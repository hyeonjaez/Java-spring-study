package org.example.functional.test;

import static org.example.functional.Iterators.generate;
import static org.example.functional.Iterators.get;
import static org.example.functional.Iterators.iterate;
import static org.example.functional.Iterators.limit;
import static org.example.functional.Iterators.map;
import static org.example.functional.Iterators.toList;
import static org.example.functional.Iterators.zip;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import org.example.Mathx;
import org.example.functional.Experiments;


enum Quality {
    BEST, GOOD, REGULAR, POOR;
}


public class MonteCarloTest {
    public static void piDemo() {

        /*
         * 몬테카를로 방법은 여러 분야에서 시뮬레이션을 하는데 널리 쓰입니다. 일단 끝없는 순열은 쓰지 않고 계산 절차만 간추려 봅니다.
         *
         * 참 거짓을 답하는 함수(실험)를 n 번 시행하고 참이 나온 횟수를 n으로 나눕니다.
         */
        BiFunction<Long, Supplier<Integer>, Double> monteCarlo = (n,
                                                                  experiment) ->
                Mathx.sum(limit(map(generate(experiment), binary -> binary), n)) / n;

        /*
         * 몬테카를로 방식으로 값을 어림잡을 수 있습니다. 두 마구잡이 수가 서로 소인지 알아보는 함수 Mathx.dirichletTest를 씁니다.
         */

        System.out.println(
                Math.sqrt(6.0 / monteCarlo.apply(100_000L, () -> Mathx.dirichletTest() ? 1 : 0)));

        /*
         * 몬테카를로 iterator를 정의합니다. 오로지 계산 방법 그 자체만을 있는 그대로 표현한 코드를 쓸 수 있습니다. 계산 시간(횟수)와 계산 공간 문제 곧 계산
         * 자원 문제를 계산 방법으로부터 격리할 수 있습니다.
         */


        Function<Supplier<Integer>, Iterator<Double>> monteCarloIterator =
                (number) -> zip(monteCarlo, iterate(1L, x -> x + 1), iterate(number, x -> x));
        /*
         *  PI 값으로 끝없이 수렴하는 수열을 표현할 수 있습니다.
         */

        Iterator<Double> pi = map(monteCarloIterator.apply(() -> Mathx.dirichletTest() ? 1 : 0),
                ratio -> Math.sqrt(6.0 / ratio));

        System.out.println(get(pi, 100_1000));
    }

    private static void potionTestWithInfiniteIterators() {
        /*
         * Iterator 패턴과 몬테카를로 방법을 실제 어떻게 쓰는지 그 장단점을 또렷이 드러내는 본보기로 약재와 약물의 관계를 모의 실험하는 예제를 다룹니다. 특히
         * 끝없는 이어지는 데이터를 순열(스트림)로 간추리는 기법은 시계열 데이터 시뮬레이션을 다루는 데 잘 들어맞습니다. 특히 이동 통신 환경에서 통신망 저편에서 흘러
         * 들어오는 수백 개의 센서 데이터 스트림을 떠올려 보면 어렵지 않게 끝없는 데이터 순열을 떠올릴 수 있습니다.
         *
         * 시뮬레이션에서는 가짜(의사) 마구잡이 수(난수)를 정해진 분포에 따라 늘어 놓은 일이 필요합니다. Mathx에 마련되어 있는 이항, 균등, 정상 분포 함수를
         * 이해합니다.
         */

        /*
         * 확률 분포와 몬테카를로 방법으로 재밌는 실험을 할 수 있습니다. 돌림병을 치료하는데 꼭 필요한 약초가 있다고 합시다.
         *
         * 좋은 약초를 발견할 확률이 herb_ratio일 때 마구잡이 수를 뽑아 이 값과 크기를 비교하면 참 거짓을 정할 수 있습니다. 이를 연속 시행하면 참 거짓의
         * 베르누이 분포를 얻을 수 있습니다. 좋은 약초 발견 가능성을 이항 분포로 뽑아냅니다.
         */
        final double herbRatio = 0.2;
        Iterator<Integer> herbAvailablities = Mathx.binaryDistribution(herbRatio);
        /*
         * 좋은 약초는 BEST 품질. 좋은 약초가 없으면 다른 약초를 여럿 섞어서 대신 쓰는데 그 품질이 고르지 않습니다. 좋은 약초가 있느냐 없느냐에 따른 약초의
         * 효과를 어림잡는 함수를 만듭니다.
         *
         * 네 가지 품질 가운데 하나가 고르게 뽑히도록 이산 균등 분포를 씁니다.
         */
        Iterator<Integer> qualities = Mathx.discreteUniformDistribution(Quality.class);

        BiFunction<Integer, Integer, Quality> biFunction = ((quality, integer) -> {
            if (quality == 1) {
                return Quality.BEST;
            }
            return Quality.values()[integer];
        });

        Iterator<Quality> herbQualities = zip(biFunction, herbAvailablities, qualities);

        EnumMap<Quality, Supplier<Double>> normalDistributions = new EnumMap<>(Quality.class);
        normalDistributions.put(Quality.BEST, () -> Mathx.randDoubleNormallyDistributed(90, 10));
        normalDistributions.put(Quality.GOOD, () -> Mathx.randDoubleNormallyDistributed(80, 20));
        normalDistributions.put(Quality.REGULAR, () -> Mathx.randDoubleNormallyDistributed(50, 30));
        normalDistributions.put(Quality.POOR, () -> Mathx.randDoubleNormallyDistributed(30, 40));

        /*
         * 약초 품질에 따라 약물의 효과가 갈리지만 약초 품질이 고르지 않으므로 약물의 효과도 편차가 있는 것이 자연스럽습니다. 실감나는 시뮬레이션을 위해서 약물 효과를
         * 네 가지로 분류하되 그 또한 마구잡이로 약효에 편차가 생기도록 네 가지 정상 분포로 표현합니다.
         */
        /* 약초의 품질에 따른 약물 효과를 마구잡이로 뽑는 함수를 만듭니다. 약물 효과는 0에서 100사이 값이므로 이 범위를 넘는 값을 잘라냅니다. */
        Iterator<Double> medicineEffects = map(herbQualities, quality -> {
            double effect = normalDistributions.get(quality).get();
            if (effect < 0) {
                return 0D;
            }
            if (effect > 100) {
                return 100D;
            }
            return effect;
        });

        /*
         * 계산이 시작되려면 시행 횟수를 정해서 약물 효과 추정치를 뽑아내고 계산 결과를 목록에 저장해야 합니다. 계산하는 방법만 적었지 실제 계산을 하지는 않았기 때문에
         * 계산 방식을 저장해둔 계산 환경 자원을 빼면 계산하는 과정에서 필요한 계산 자원이 조금도 소비되지 않았다는 사실을 정확히 인식하고 이해하는 것이 중요합니다.
         * 계산하는 방법과 계산을 나누면 어떤 방식으로 프로그램을 설계할 수 있는지가 잘 드러나 있습니다.
         *
         * limit로 7000번 계산을 합니다. 끝없는 계산 순열 medicineEffects가 100번으로 끝나도록 만든 뒤에 Iterators.toList를 써서
         * 계산 결과를 순서대로 하나씩 목록에 저장하는 방식으로 실제 계산을 합니다. 계산 순열 effects에는 다른 계산 순열 herbQualities가 연결되어 있고
         * 이는 다시 herbAvaliablities로 연결되어 있기 때문에 한 계산이 다른 계산으로 이어집니다.
         */
        toList(limit(medicineEffects, 100));
    }

    private static void potionTestWithExperiments() {
        /*
         * 약효 모의 실험은 잘 되지만 실험은 결과 만큼 과정에 대한 정보도 중요합니다. 계산 과정에 쓴 데이터들 곧 약초와 품질, 그에 따른 약물의 품질, 그리고 약효에
         * 대한 데이터가 될 수 있는 한 상세하게 보고서로 나와야 쓸모있는 실험이 됩니다. Iterator를 쓰는 코드 얼개를 조금도 망가뜨리지 않고 필요한 정보만
         * 저장했다가 꺼내보는 방법이 필요합니다.
         *
         * 계산과 저장을 가능한 하지 않고 미루는 방식과 계산 과정에서 얻은 정보를 적절히 저장하는 두 가지 다른 방식을 한 데 엮어 쓰는 본보기가 됩니다. 문제는 그에
         * 맞는 풀이 방법이 제 각기 다를 수 있습니다. 굳이 한 가지 방법만을 고집할 필요가 없습니다.
         */

        /*
         * 계산 과정에서 얻은 데이터 곧 횟수와 합을 저장하기 때문에 필요할 때 꺼내 쓸 수 있으면서도 InfiniteIterator로 동작하는 Experiments
         * class를 만들 수 있습니다.
         *
         * 코드 구조가 거의 바뀌지 않았습니다. Experiments로 덧 쒸운 코드 말고는 달라진 부분이 거의 없습니다. 하지만 이 번에는 계산 정보를 저장했다가 출력할
         * 준비가 되어 있습니다.
         */

        final double herbRatio = 0.2;
        Experiments<Integer> herbAvailablities = // TODO: Experiments 만들기
                new Experiments<>(Mathx.binaryDistribution(herbRatio), "herb availabilities",
                        "binomial distribution");

        Experiments<Integer> herbQualities = new Experiments<>(
                zip((available, effect) -> available == 1 ? Quality.BEST.ordinal() : effect,
                        herbAvailablities, Mathx.discreteUniformDistribution(Quality.class)),
                "herb qualities", "discrete uniform distribition");

        EnumMap<Quality, Experiments<Double>> normalDistributions = new EnumMap<>(Quality.class);
        String normalDistribution = "normal distribition";
        normalDistributions.put(Quality.BEST, new Experiments<>(Mathx.normalDistribution(90, 10),
                "best effect", normalDistribution));
        normalDistributions.put(Quality.GOOD, new Experiments<>(Mathx.normalDistribution(80, 20),
                "good effect", normalDistribution));
        normalDistributions.put(Quality.REGULAR, new Experiments<>(Mathx.normalDistribution(50, 30),
                "regular effect", normalDistribution));
        normalDistributions.put(Quality.POOR, new Experiments<>(Mathx.normalDistribution(30, 40),
                "poor effect", normalDistribution));

        Iterator<Double> medicineEffects = map(herbQualities, quality -> {
            double effect = normalDistributions.get(Quality.values()[quality]).next();
            if (effect < 0) {
                return 0D;
            }
            if (effect > 100) {
                return 100D;
            }
            return effect;
        });

        toList(limit(medicineEffects, 7000)); // 실제 계산은 여기서 일어납니다!!!

        /*
         * 모든 계산이 마무리되었으므로 계산 결과를 볼 수 있습니다. MonteCarlo 클래스가 없다면 계산 순열을 끝없이 늘어놓는 일과 계산 과정을 기록하는 일, 이
         * 둘을 한 꾸러미로 묶어내기가 (언제나 그렇듯이 한 방법으로 다른 방법을 완전히 대체하는 할 수 있지만) 무척 번거롭습니다. 문제마다 알맞은 방법을 골라서 서로
         * 잘 어울리도록 짜 맞추면 프로그램의 얼개가 아주 튼튼해집니다. 성능을 시험하고 고장난 곳을 찾아서 고치기에도 좋은 짜임새를 갖추게 됩니다.
         *
         * 좋은 약초를 얻을 확률은 얼마나 될까요?
         */
        System.out.println("Herb availability");
        herbAvailablities.report();
        System.out.println();

        /* 좋은 약초가 있고 없고에 따른 약재의 품질은 어떤가요? */
        System.out.println("Herb quality (Excellent = 0, Good = 1, Marginal = 2, Poor = 3): ");
        herbQualities.report();
        System.out.println();

        /* 약초 품질에 따른 약물의 효과는 어떻게 분포되나요? */
        System.out.println("Potion effects by the 4 quality categories: ");
        for (Quality quality : Quality.values()) {
            normalDistributions.get(quality).report();
            System.out.println();
        }
    }

    public static void main(String[] args) {
//        MonteCarloTest.piDemo();
        MonteCarloTest.potionTestWithInfiniteIterators();
        MonteCarloTest.potionTestWithExperiments();
    }

}
