
package converters;

import java.net.URLDecoder;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import security.Authority;

@Component
@Transactional
public class StringToAuthorityConverter implements Converter<String, Authority> {

	@Override
	public Authority convert(final String source) {
		Authority result;

		if (source == null)
			result = null;
		else
			try {
				result = new Authority();
				result.setAuthority(URLDecoder.decode(source, "UTF-8"));
			} catch (final Exception e) {
				throw new RuntimeException(e);
			}
		return result;
	}

}
