import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ComputeEngine extends UnicastRemoteObject implements Compute {

    protected ComputeEngine() throws RemoteException {
        super();
    }

    @Override
    public double computePi(int precision) throws RemoteException {
        // Використання формули Мачіна для обчислення Pi з заданою точністю
        BigDecimal pi = new BigDecimal(0);
        BigDecimal one = new BigDecimal(1);
        BigDecimal arctan1_5 = arctan(new BigDecimal(1).divide(new BigDecimal(5), precision + 5, RoundingMode.HALF_UP), precision + 5);
        BigDecimal arctan1_239 = arctan(new BigDecimal(1).divide(new BigDecimal(239), precision + 5, RoundingMode.HALF_UP), precision + 5);

        pi = pi.add(arctan1_5.multiply(new BigDecimal(4)));
        pi = pi.subtract(arctan1_239);
        pi = pi.multiply(new BigDecimal(4));
        pi = pi.setScale(precision, RoundingMode.HALF_UP);

        return pi.doubleValue();
    }

    private BigDecimal arctan(BigDecimal x, int scale) {
        BigDecimal result, term;
        BigDecimal x2 = x.multiply(x);
        int i = 1;
        result = x;
        term = x;
        do {
            term = term.multiply(x2).divide(new BigDecimal((2 * i + 1)), scale, RoundingMode.HALF_UP);
            if (i % 2 != 0) {
                result = result.subtract(term);
            } else {
                result = result.add(term);
            }
            i++;
        } while (term.compareTo(BigDecimal.ZERO) != 0);
        return result;
    }
}
