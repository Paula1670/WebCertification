
package converters;

import java.net.URLEncoder;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.DataType.TarjetaDeCredito;

@Component
@Transactional
public class TarjetaDeCreditoToStringConverter implements Converter<TarjetaDeCredito, String> {

	@Override
	public String convert(final TarjetaDeCredito source) {
		String result;
		StringBuilder builder;
		if (source == null) {
			result = null;
			return result;
		}

		try {
			builder = new StringBuilder();

			builder.append(URLEncoder.encode(source.getTitular(), "UTF-8"));
			builder.append("|");
			builder.append(URLEncoder.encode(source.getMarca(), "UTF-8"));
			builder.append("|");
			builder.append(URLEncoder.encode(source.getNumero(), "UTF-8"));
			builder.append("|");
			builder.append(URLEncoder.encode(Integer.toString(source.getMesCaducidad()), "UTF-8"));
			builder.append("|");
			builder.append(URLEncoder.encode(Integer.toString(source.getAnioCaducidad()), "UTF-8"));
			builder.append("|");
			builder.append(URLEncoder.encode(Integer.toString(source.getCvv()), "UTF-8"));

			result = builder.toString();
		} catch (final Exception e) {
			///Pasamos un valor vacio para saber si se han pasado datos o no
			result = "";
		}

		return result;
	}

}
