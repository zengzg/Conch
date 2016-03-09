package zzg.conch;

import android.content.res.Resources;
import android.view.ViewConfiguration;

import org.junit.Test;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

  @Test
  public void addition_isCorrect() throws Exception {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://vivid-inferno-8287.firebaseio.com/")
        .build();

    Demo demo = retrofit.create(Demo.class);

    Call<String> call = demo.getName();

    System.out.print(call.execute().body());

    ViewConfiguration.get(null).getScaledTouchSlop();
  }

  public interface Demo {

    @GET("/account/")
    Call<String> getName();

  }


  public class User {
    int id;
    String name;

    public String getName() {
      return name;
    }
  }
}