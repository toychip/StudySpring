package mvc2.typeconverter.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class StringToIntegerConverter implements Converter<String, Integer> {

    @Override   // 문자 -> 숫자
    public Integer convert(String source) {
        log.info("convert source {}", source);
        return Integer.valueOf(source);
    }

}
