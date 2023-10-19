package study.nextstep.domain.utils;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AsyncExecutor {
    @Async
    public <T> CompletableFuture<T> exec(Supplier<T> supplier){
        CompletableFuture<T> future = new CompletableFuture<>();
        try {
            log.info("[Async exec - 시작]");
            future.complete(supplier.get());
            log.info("[Async exec - 종료]");
        } catch (Exception e){
            log.info("[Async exec - 중단(에러발생)]");
            future.completeExceptionally(e);
        }

        return future;
    }
}
