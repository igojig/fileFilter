package ru.igojig.converter;

import lombok.Setter;
import ru.igojig.ReadedObject;

@Setter
 abstract class AbstractConvertor {
    protected AbstractConvertor next;
    public abstract ReadedObject convert(String str);
}
