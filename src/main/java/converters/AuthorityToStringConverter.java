
package converters;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import security.Authority;

@Component
@Transactional
class AuthorityToStringConverter implements Converter<Authority, String> {

	@Override
	public String convert(final Authority source) {
		String result;

		if (source == null) {
			result = null;
			return result;
		}

		try {
			result = URLEncoder.encode(source.getAuthority(), "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			result = null;
		}

		return result;
	}

}
