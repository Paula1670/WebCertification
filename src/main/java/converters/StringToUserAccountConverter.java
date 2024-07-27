
package converters;

import java.net.URLDecoder;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import security.Authority;
import security.UserAccount;

@Component
@Transactional
public class StringToUserAccountConverter implements Converter<String, UserAccount> {

	@Override
	public UserAccount convert(final String source) {
		UserAccount result;
		String parts[];

		if (source == null)
			return null;

		try {
			result = new UserAccount();
			parts = source.split("\\|");
			result.setUsername(URLDecoder.decode(parts[0], "UTF-8"));
			result.setPassword(URLDecoder.decode(parts[1], "UTF-8"));

			///Authorities
			for (int i = 2; i < parts.length; i++) {
				final Authority aux = new Authority();
				aux.setAuthority(URLDecoder.decode(parts[i], "UTF-8"));

				result.addAuthority(aux);
			}

		} catch (final Exception e) {
			result = null;
			System.out.println("String obtenido: salta una excepción: " + e.getMessage());
		}

		return result;
	}

}
