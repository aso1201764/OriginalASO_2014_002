package jp.ac.st.asojuku.original2014002;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MaintenanceActivity extends Activity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maintenance);
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		// ボタンをIDで探してボタン変数をつくる
		Button btn_back = (Button)findViewById(R.id.btn_back);
		btn_back.setOnClickListener(this);

		Button btn_delete = (Button)findViewById(R.id.btn_delete);
		btn_delete.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ

		// 生成して代入用のIntentインスタンス変数を用意
		Intent intent = null;
		switch(v.getId()){	//どのボタンが押されたか判定
			case R.id.btn_back:	//btn_backが押された
				// インテントのインスタンスを生成
				intent = new Intent(MaintenanceActivity.this, MainActivity.class);
				// 次画面のアクティビティ起動
				startActivity(intent);
				break;

			case R.id.btn_delete:


				break;
		}
	}

}
