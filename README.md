# P/V操作java实现
## The producer-consumer problem

- 注：src/start.java

> 桌子上有一只盘子，每次只能向其中放入一个水果。爸爸专向盘子中放苹果，妈妈专向盘子中放 橘子，儿子专等着吃盘子中的橘子，女儿专等着吃盘子中的苹果。只有盘子空时，爸爸或妈妈才 可向盘子中放一个水果。仅当盘子中有自己需要的水果时，儿子或女儿可以从盘子中取出水果。 用PV操作实现上述过程。

伪代码
```
Var mutex, empty, banana, apple:semaphore:=1, 10, 0, 0;
plate:array[0, ....., 9] of fruit
in, out, integer:=0, 0;
parbegin:
    father:begin
        repeat
            prepare a fruit
            wait(empty);
            wait(mutex);
            in:=find a empty place
            plate(in):=nextp;
            signal(mutex);
            signal(banana|apple);
        until false;
    end;

    son:begin
        repeat
            prepare a fruit
            wait(banana);
            wait(mutex);
            out:=the recent banana;
            nextc:=plate(out);
            remove the banana;
            signal(mutex);
            signal(banana);
        until false;
    end;

    daughter:begin
        repeat
            prepare a fruit
            wait(apple);
            wait(mutex);
            out:=the recent apple;
            nextc:=plate(out);
            remove the apple;
            signal(mutex);
            signal(apple);
        until false;
    end;
```
