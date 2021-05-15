package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//        basePackages = "hello.core.member",// 탐색할 패키지의 시작 위치 지정 , default = @ComponentScan이 붙은 클래스의 패키지 부터
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)//AppConfig.java 및 test 하면서 생성한  것들 제외 시키기 위해.
)
public class AutoAppConfig {

    @Bean(name = "memoryMemberRepository")//수동이 우선권을 가짐
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
