package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//구성영역

/**
 * SRP단일 책임 원칙을 따르면서 관심사를 분리함
 * 구현 객체를 생성하고 연결하는 책임은 AppConfig 가 담당
 * 클라이언트 객체는 실행하는 책임만 담당(MemberServiceImpl 등등)
 */
@Configuration//application의 구성정보
public class AppConfig {

    @Bean //스프링 컨테이너에 등록
    //생성자 주입
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("cal AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    //생성자 주입
    public OrderService orderService () {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
//        return null;
    }

    @Bean
    //정책이 바뀌면 AppConfig에서 매서드만 수정
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
