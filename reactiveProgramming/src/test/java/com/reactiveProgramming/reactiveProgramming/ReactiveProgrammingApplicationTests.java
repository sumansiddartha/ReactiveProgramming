package com.reactiveProgramming.reactiveProgramming;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;



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
	
	@Test
	public void WorkingWithMono() {
		System.out.println("Mono Testing");
		//Mono  has 0...1 items
		Mono<String> m1 = Mono.just("This is check for mono").log();
		Mono<Object> errorMono = Mono.error(new RuntimeException("Error !!"));
		m1.subscribe(data->{System.out.println("This is data "+data);});
		//errorMono.subscribe(System.out::println);
		}
	
	@Test
	public void workingWithCombiningMultipleMonos() {
		Mono<String> m1 = Mono.just("This is a check for Mono");
		Mono<String> m2 = Mono.just("This is the subscription for mono please");
		Mono<Integer> m3 = Mono.just(123456);
		Mono<Tuple2<String, String>> combinedMono = Mono.zip(m1, m2);
		combinedMono.subscribe(System.out::println);
		combinedMono.subscribe(data->{
			System.out.println(data.getT1());
			System.out.println(data.getT2());
		});
		Mono<Tuple3<String, String, Integer>> combinedMono2 = Mono.zip(m1, m2,m3);
		combinedMono2.subscribe(data->{
			System.out.println(data.getT1());
			System.out.println(data.getT2());
			System.out.println(data.getT3());
		});
		//ZipWith
		m1.zipWith(m2).subscribe(data->{
			System.out.println(data.getT1());
			System.out.println(data.getT2());
		});
		
	}
	
	@Test
	public void workingWithMonoMapAndFlatMap() throws InterruptedException {
		
		Mono<String> m1 = Mono.just("This is a check for Mono");
		Mono<String> m2 = Mono.just("This is the subscription for mono please");
		Mono<Integer> m3 = Mono.just(123456);
		Mono<String> resultMono = m1.map(item->item.toUpperCase());
		//map is used to transform using sync
		resultMono.subscribe(System.out::println);
		Mono<String[]> resultFlatMapExample = m1.flatMap(valueM1->Mono.just(valueM1.toUpperCase().split(" ")));
		resultFlatMapExample.subscribe(data->{
			for(String s:data) {
				System.out.println(s);
			}
		});	
		System.out.println("-----------------------------------------");
		Flux<String> flatMapMany = m1.flatMapMany(val->Flux.just(val.split(" ")));
		flatMapMany.subscribe(data->{
			System.out.println(data);
		});
		Flux<String> stringFlux = m1.concatWith(m2).log().delayElements(Duration.ofMillis(2000));
		
		stringFlux.subscribe(data->{
			System.out.println(Thread.currentThread().getName());
			System.out.println(data);
		});
		Thread.sleep(5000);
	}
	

}
