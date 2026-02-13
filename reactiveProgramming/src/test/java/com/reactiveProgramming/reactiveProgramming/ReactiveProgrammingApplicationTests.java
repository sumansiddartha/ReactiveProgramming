package com.reactiveProgramming.reactiveProgramming;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@SpringBootTest
class ReactiveProgrammingApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("test started");
	Mono<String> monoPublisher = Mono.just("testing");
	monoPublisher.subscribe(new CoreSubscriber() {

		@Override
		public void onNext(Object t) {
			System.out.println("data: "+t);
		}

		@Override
		public void onError(Throwable t) {
			System.out.println("error "+t.getMessage());
			
		}

		@Override
		public void onComplete() {
			System.out.println("Test Completed");
			
		}

		@Override
		public void onSubscribe(Subscription s) {
			System.out.println("Subscription done");
			s.request(1);
			
		}
	});
	}

}
