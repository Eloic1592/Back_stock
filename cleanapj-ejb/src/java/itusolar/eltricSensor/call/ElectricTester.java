package itusolar.eltricSensor.call;

import itusolar.eltricSensor.ElectricState;
import itusolar.prepare.HServiceManager;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ElectricTester extends HServiceManager implements ElectricTesterSignature {


    @Override
    public void evaluate(ElectricState[] states) throws IOException {
        CompletableFuture<Void>[] futures = new CompletableFuture[states.length];
        int index = 0;
        for (ElectricState state : states) {
            ElectricTestExecutor executor = this.toExecutor(state);
            futures[index] = CompletableFuture.runAsync(executor);
            index++;
        }
        CompletableFuture<Void> allRequestsFuture = CompletableFuture.allOf(futures);
        try {
            allRequestsFuture.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | java.util.concurrent.TimeoutException e) {
            e.printStackTrace();
        }
    }

    public ElectricTestExecutor toExecutor(ElectricState state) {
        return new ElectricTestExecutor(state);
    }
}
