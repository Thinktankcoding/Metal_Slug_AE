package jing.game.metalslugae;

import org.newdawn.slick.SlickActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;

public class GameActivity extends SlickActivity {

	int width;
	int height;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		height = metrics.heightPixels;
		width = metrics.widthPixels;
		super.onCreate(savedInstanceState);

		start(new MetalSlug(width, height), width, height);
	}

}
