package de.kreth.vereinsmeisterschaftprog.data.calculatoren;

import java.math.BigDecimal;
import java.util.List;

import de.kreth.vereinsmeisterschaftprog.business.GruppeChangeListener;
import de.kreth.vereinsmeisterschaftprog.data.Value;
import de.kreth.vereinsmeisterschaftprog.data.ValueType;
import de.kreth.vereinsmeisterschaftprog.data.Wertung;

public class WertungCalculatorFactory {

	private static final MaxValueCalculator dreisprungCalculator = new DreisprungCalculator();

	private static final MaxValueCalculator seilsprungCalculator = new SeilspringenCalculator();

	public static GruppeChangeListener[] getGruppeListeners() {
		return new GruppeChangeListener[] { dreisprungCalculator, seilsprungCalculator };
	}

	public static BigDecimal calculate(Wertung wertung) {
		BigDecimal result = BigDecimal.ZERO;

		List<Value> hdWerte = wertung.getByType(ValueType.HD);

		if (hdWerte == null || hdWerte.isEmpty()) {

			switch (getAnzahlWerte(wertung)) {
			case 3:
				result = new WertungCalculatorFor3Values().calculate(wertung);
				break;

			case 4:
				result = new WertungCalculatorFor4Values().calculate(wertung);
				break;

			case 5:
				result = new WertungCalculatorFor5Values().calculate(wertung);
				break;

			default:
				break;
			}
		}
		else {

			switch (getAnzahlWerte(wertung)) {
			case 2:
				result = new WertungCalculatorFor2ValuesHd().calculate(wertung);
				break;

			case 3:
				result = new WertungCalculatorFor3ValuesHd().calculate(wertung);
				break;

			case 4:
				result = new WertungCalculatorFor4ValuesHd().calculate(wertung);
				break;

			default:
				break;
			}
		}

		result = result.add(dreisprungCalculator
				.calculate(wertung));
		result = result.add(seilsprungCalculator
				.calculate(wertung));
		return result;
	}

	private static int getAnzahlWerte(Wertung wertung) {
		return wertung.getByType(ValueType.HALTUNG).size();
	}
}
