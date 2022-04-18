package io.github.scrumboot.security.endpoint;

import io.github.scrumboot.langs.Strings;
import io.github.scrumboot.security.AccessToken;
import io.github.scrumboot.security.AuthorizationTokenManager;
import io.github.scrumboot.security.social.SocialAuthenticationManager;
import io.github.scrumboot.security.social.SocialRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限端点
 *
 * @author bingdyee
 * @since 2022/03/11
 */
@Tag(name = "权限接口")
@RestController
@RequestMapping("/v1/auth")
public class AuthEndpoint {

    private final AuthorizationTokenManager authorizationTokenManager;

    private final SocialAuthenticationManager socialAuthenticationManager;

    public AuthEndpoint(AuthorizationTokenManager authorizationTokenManager, SocialAuthenticationManager socialAuthenticationManager) {
        this.authorizationTokenManager = authorizationTokenManager;
        this.socialAuthenticationManager = socialAuthenticationManager;
    }

    @Operation(summary = "退出")
    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        String token = AuthorizationTokenManager.extractAccessToken(request);
        if (Strings.isNotBlank(token)) {
            authorizationTokenManager.removeAccessToken(token);
        }
    }

    @Operation(summary = "刷新令牌")
    @PostMapping("/refreshToken")
    public AccessToken refreshToken(@RequestParam String refreshToken) {
        return authorizationTokenManager.refreshAccessToken(refreshToken);
    }

    @Operation(summary = "三方账号注册登录")
    @Parameters({
            @Parameter(name = "socialType", description = "平台类型不能为空：mobile-手机、wechat-微信、apple-苹果、qq-QQ、weibo-微博", required = true, in= ParameterIn.PATH),
            @Parameter(name = "code", description = "授权码", required = true)
    })
    @GetMapping("/{socialType}/callback")
    public AccessToken socialLogin(@PathVariable String socialType, @RequestParam("code") String code) {
        Authentication authentication = socialAuthenticationManager.authenticate(new SocialRequest(socialType, code));
        return authorizationTokenManager.createAccessToken(authentication);
    }

}
