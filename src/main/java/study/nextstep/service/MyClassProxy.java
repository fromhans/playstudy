package study.nextstep.service;

public class MyClassProxy extends MyClass{


    @Override
    public void logging() {
        super.logging();
    }

    @Override
    public void transactionLogging() {
        //tx.start
        super.transactionLogging();
        //tx.commit
    }
}
