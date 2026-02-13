###<center>Reactive Stream Specification</center>

Reactive Programming has some rules these rules is known as Specification.

There are 4 main interfaces
* **Publisher**
* **Subscriber**
* Subscription
* Processor

#### Publisher
Act as datasource .

#### Subscriber
Act as data receiver .

#### Subscription
Request the data from the publisher or cancel the request.
2 void methods 
void request(long n);

void cancel();

#### Processor
Processor interface is combination of both Publisher and Subscriber interfaces.
public interface Processor<T,R> extends Subscriber<T>,Publisher<R>{}

