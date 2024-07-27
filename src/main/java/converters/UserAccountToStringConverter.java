
package converters;

import java.net.URLEncoder;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import security.Authority;
import security.UserAccount;

@Component
@Transactional
public class UserAccountToStringConverter implements Converter<UserAccount, String> {

	@Override
	public String convert(final UserAccount source) {
		String result;
		final StringBuilder builder;

		if (source == null)
			result = null;
		else
			try {
				final Iterator<Authority> ite = source.getAuthorities().iterator();
				builder = new StringBuilder("");
				builder.append(URLEncoder.encode(source.getUsername(), "UTF-8"));
				builder.append("|");
				builder.append(URLEncoder.encode(source.getPassword(), "UTF-8"));

				///Authorities
				Authority Aux;
				while (ite.hasNext()) {
					Aux = ite.next();
					builder.append("|");
					builder.append(URLEncoder.encode(Aux.getAuthority(), "UTF-8"));
				}
				result = builder.toString();

			} catch (final Exception e) {
				result = null;
			}

		return result;
	}

}
