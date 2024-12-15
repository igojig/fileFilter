package ru.igojig.processing;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import ru.igojig.ReadedObject;
import ru.igojig.exceptions.DataWriteException;
import ru.igojig.exceptions.WriterOpenException;
import ru.igojig.reader.DataReader;
import ru.igojig.args.EffectiveParameters;
import ru.igojig.converter.Convertor;
import ru.igojig.statistics.StatisticFactory;
import ru.igojig.writers.DataWriter;
import ru.igojig.writers.WriterFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Data
@Log4j2

public class Process {
    private final EffectiveParameters effectiveParameters;

    private DataReader dataReader;
    private DataWriter dataWriter;

    private Convertor convertor = new Convertor();
    private WriterFactory writerFactory = new WriterFactory();


    public Process(EffectiveParameters effectiveParameters) {
        this.effectiveParameters = effectiveParameters;
        //TODO сделать Paths
        writerFactory.initFactory(effectiveParameters);
        try {
            writerFactory.openWriters();
        } catch (WriterOpenException e) {
            log.error("Error open {}, cause: {}", e.getBaseWriter().getPath(), e.getMessage());
            closeWritersAndExit();
        }
    }


    public void process() {
        List<Path> inputs = effectiveParameters.getInputFilenames();

        for (Path path : inputs) {
            dataReader = new DataReader(path, this::processLine);
            try {
                dataReader.open();
            } catch (IOException e) {
                log.error("""
                        Error open file {}: {}
                        Will proceed next input file.
                        """, path, e.getMessage());
                continue;
            }
            log.info("Processing file: {}", path);
            try {
                dataReader.read();
            } catch (IOException e) {
                log.error("""
                        Error read from {}, cause: {}
                        Proceed next input file.
                        """, path, e.getMessage());
                dataReader.close();
                continue;
            }
            dataReader.close();

        }

        // successfully write all data
        writerFactory.closeAll();


        //show stat data
        StatisticFactory.showAll(effectiveParameters.getStatisticType());


    }

    public void processLine(String line) {
        ReadedObject readedObject = parse(line);
        write(readedObject);
        stat(readedObject);
    }

    private ReadedObject parse(String line) {
        return convertor.convert(line);
    }

    private void write(ReadedObject readedObject) {
        dataWriter = writerFactory.getWriter(readedObject.getType());
        try {
            dataWriter.write(readedObject);
        } catch (DataWriteException e) {
            log.fatal("Error write to {}, cause: {}", e.getBaseWriter().getPath(), e.getMessage());
            closeAllAndExit();
        }
    }

    private void stat(ReadedObject readedObject) {
        StatisticFactory.getStat(readedObject.getType(), effectiveParameters.getStatisticType()).accumulate(readedObject);
    }


    private void closeAllAndExit() {
        writerFactory.closeAll();
        dataReader.close();
        System.exit(2);
    }

    private void closeWritersAndExit() {
        writerFactory.closeAll();
        System.exit(2);
    }
}
