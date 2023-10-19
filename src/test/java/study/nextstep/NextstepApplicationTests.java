package study.nextstep;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import study.nextstep.domain.entity.EntityA;
import study.nextstep.domain.entity.EntityB;
import study.nextstep.domain.entity.Play;
import study.nextstep.domain.repository.jpa.EntityARepository;
import study.nextstep.domain.repository.jpa.EntityBRepository;
import study.nextstep.service.PlayRx;
import study.nextstep.service.PlayTx;

@SpringBootTest
@Slf4j
class NextstepApplicationTests {
//
//	@Autowired
//	EntityManager em;

	@Autowired
	private PlayRx playRx;
	@Autowired
	private PlayTx playTx;
	@Autowired
	private EntityARepository entityARepository;
	@Autowired
	private EntityBRepository entityBRepository;

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

	@Test
	void 테스트_entityListener연계동작() throws InterruptedException {
		//given
		//EntityA entityA = new EntityA("entityA");

		EntityB entityB = new EntityB("entityB");

		//when
		//entityARepository.save(entityA);
		entityBRepository.save(entityB);
		Thread.sleep(1000L);
		System.out.println("test ended");
	}


	@Test
	void 테스트_orphanRemoval() throws InterruptedException {
		//given
		//EntityA entityA = new EntityA("entityA");
		EntityA entityA = new EntityA("entityA2");
		EntityB entityB = new EntityB("entityB2");
		entityB.setEntityAId(entityA);

		//when
		entityARepository.save(entityA);
		entityBRepository.save(entityB);
	}

	@Test
	void 테스트_orphanRemoval_부모엔티티삭제시_자식엔티티삭제여부(){
		//given
		EntityA entityA = entityARepository.findById("067d257a-a933-4803-a7fa-e8a0c742fa1d").orElse(null);

		entityARepository.delete(entityA);
	}

	@Test
	void slf4jtest(){
		log.error("{}, {}", "a");
		log.error("""
			{}
			{}""", "bbbb");
	}

	@Test
	void forTest(){
		List<String> asdf = null;
		for(String s : asdf){
			System.out.println("test" + s);
		}
	}

	@Test
	void forTest2(){
		MultiValueMap<String, String> testMap = null;
		System.out.println(testMap == null);
		//System.out.println(testMap.isEmpty());

		testMap.add("a","b");
		System.out.println(testMap == null);
		System.out.println(testMap.isEmpty());
	}
}
