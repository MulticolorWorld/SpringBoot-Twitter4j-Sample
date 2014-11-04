package jp.dip.jimanglaurant.springboot.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {

    Twitter twitter;
    RequestToken requestToken;

    @RequestMapping(method = RequestMethod.GET)
    String index(){
        return "index";
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    void login(RedirectAttributes attributes,HttpServletResponse response){

        try {
            twitter = new TwitterFactory().getInstance();
            twitter.setOAuthConsumer("こんしゅまーきー","こんしゅまーしーくれっと");
            requestToken = twitter.getOAuthRequestToken("http://localhost:8080/callback");
            response.sendRedirect(requestToken.getAuthenticationURL());
        } catch (TwitterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "callback",method = RequestMethod.GET)
    String callback(Model model,HttpServletRequest request){

        String verifier = request.getParameter("oauth_verifier");

        try {
            twitter.getOAuthAccessToken(requestToken, verifier);
            model.addAttribute("screenName",twitter.getScreenName());
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        return "mypage";
    }
}
