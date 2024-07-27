
package converters;

import java.net.URLDecoder;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.DataType.TarjetaDeCredito;

@Component
@Transactional
public class StringToTarjetaDeCreditoConverter implements Converter<String, TarjetaDeCredito> {

	@Override
	public TarjetaDeCredito convert(final String source) {
		TarjetaDeCredito result;
		String parts[];
		try {

			parts = source.split("\\|");
			if (source == null || source.isEmpty())
				result = null;
			else {
				///Tratamiento el caso donde tenemos todos los datos de TarjetaDeCredito

				result = new TarjetaDeCredito();
				result.setTitular(URLDecoder.decode(parts[0], "UTF-8"));
				result.setMarca(URLDecoder.decode(parts[1], "UTF-8"));
				result.setNumero(URLDecoder.decode(parts[2], "UTF-8"));
				result.setMesCaducidad(Integer.valueOf(URLDecoder.decode(parts[3], "UTF-8")));
				result.setAnioCaducidad(Integer.valueOf(URLDecoder.decode(parts[4], "UTF-8")));
				result.setCvv(Integer.valueOf(URLDecoder.decode(parts[5], "UTF-8")));
			}
		} catch (final Exception e) {
			result = null;
		}

		return result;
	}

}
