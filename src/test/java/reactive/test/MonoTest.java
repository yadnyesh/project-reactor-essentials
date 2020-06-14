package reactive.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Slf4j
public class MonoTest {

    @Test
    public void test() {
        log.info("Everything working as intended....:)");
    }

    @Test
    public void monoSubscriber() {
        String name = "Yadnyesh Juvekar";
        Mono<String> mono = Mono.just(name);
        mono.subscribe();

        StepVerifier.create(mono)
                .expectNext("Yadnyesh Juvekar")
                .verifyComplete();

        log.info("Mono working as intended....:)");
    }

    @Test
    public void monoSubscribeConsumer() {
        String name = "Yadnyesh Juvekar";
        Mono<String> mono = Mono.just(name).log();
        mono.subscribe(s -> log.info("Value is {}", s));

        StepVerifier.create(mono)
                .expectNext("Yadnyesh Juvekar")
                .verifyComplete();

        log.info("Mono working as intended....:)");
    }

    @Test
    public void monoSubscribeConsumerError() {
        String name = "Yadnyesh Juvekar";
        Mono<String> mono = Mono.just(name)
                .map(s -> {throw new RuntimeException("Testing mono with Error");});
        mono.subscribe(s -> log.info("Value is {}", s), s -> log.error("something went wrong..."));
        mono.subscribe(s -> log.info("Value is {}", s), Throwable::printStackTrace);

        StepVerifier.create(mono)
                .expectError(RuntimeException.class)
                .verify();

        log.info("Mono working as intended....:)");
    }
}
