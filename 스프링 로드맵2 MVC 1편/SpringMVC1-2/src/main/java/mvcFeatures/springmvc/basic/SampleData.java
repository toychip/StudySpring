package mvcFeatures.springmvc.basic;

import lombok.Data;

@Data
// data 애노테이션을 활용하면 @Getter, @Setter, @ToString, @RequiredArgsConstructor 을 자동으로 적용해
public class SampleData {

    private String username;
    private int age;

}
