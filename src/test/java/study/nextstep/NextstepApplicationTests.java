package study.nextstep;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.transaction.annotation.Transactional;
import study.nextstep.domain.entity.Play;
import study.nextstep.service.PlayRx;
import study.nextstep.service.PlayTx;

@SpringBootTest
class NextstepApplicationTests {

	@Autowired
	private PlayRx playRx;
	@Autowired
	private PlayTx playTx;

	@Test
	@Transactional
	void isQueryExcutedInTransaction() throws InterruptedException {
		CountDownLatch isSaved = new CountDownLatch(1);
		CountDownLatch isChanged = new CountDownLatch(1);
		CountDownLatch isEnded = new CountDownLatch(1);

		//given
		Play a = Play.builder()
			.testText("a").build();

		//when
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.submit(() -> {
			try {
				isSaved.await();
				Play aa = playTx.findByTestText("a");
				System.out.println(Thread.currentThread().getName() + "] a == aa ? : " + (a == aa));
				aa.setTestText("aa");
				playTx.save(aa);
				//Thread.sleep(3000L);
				System.out.println(Thread.currentThread().getName() + "] isChanged count down");
				Thread.sleep(1000L);
				isChanged.countDown();
				isEnded.countDown();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		playTx.saveAndFlush(a);
		isSaved.countDown();
		isChanged.await();
		Play b = playRx.findByTestText("aa");
		System.out.println(Thread.currentThread().getName() + "] a.text: " + b.getTestText());
		isEnded.await();
	}

	@Test
	@Transactional
	void saveTest() throws InterruptedException {
		Play a = Play.builder()
			.testText("a").build();
		playTx.saveAndFlush(a);
		Thread.sleep(10000L);
	}

	@Test
	@Transactional
	void beforeAfterFlush() throws InterruptedException {
		Play a = Play.builder()
			.testText("a").build();
		playTx.save(a);
		Thread.sleep(10000L);
	}


	@Test
	@Transactional
	void isChangeViewed() throws InterruptedException, NotFoundException {
		CountDownLatch isSaved = new CountDownLatch(1);
		CountDownLatch isChanged = new CountDownLatch(1);
		CountDownLatch isReaded = new CountDownLatch(1);
		CountDownLatch isEnded = new CountDownLatch(1);

		//given
		Play a = Play.builder()
			.testText("a").build();

		//when
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.submit(() -> {
			try {


				playTx.saveAndFlushRequiresNew(a);
				isSaved.countDown();
				isReaded.await();
				a.setTestText("bb");
				playTx.saveAndFlushRequiresNew(a);
				Thread.sleep(1000L);
				isChanged.countDown();
				isEnded.await();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		isSaved.await();
		Play aa = playTx.findByTestText("a");
		System.out.println("ID: " + aa.getId() + " / text: " + aa.getTestText());
		isReaded.countDown();
		isChanged.await();
		Thread.sleep(1000L);
		Play b = playTx.find(aa.getId());
		Play bb = playTx.findRequiresNew(aa.getId());
		System.out.println("ID: " + b.getId() + " / text: " + b.getTestText());
		System.out.println("Requires New ID: " + bb.getId() + " / text: " + bb.getTestText());
		isEnded.countDown();
	}
}
