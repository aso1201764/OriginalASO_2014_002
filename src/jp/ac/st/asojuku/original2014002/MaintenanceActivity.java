package jp.ac.st.asojuku.original2014002;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MaintenanceActivity extends Activity implements
	View.OnClickListener, AdapterView.OnItemClickListener {

	// SQLiteデータベース空間を操作するインスタンス変数を宣言
	SQLiteDatabase sdb = null;
	// MySQLiteOpenHelperを操作するインスタンス変数を宣言
	MySQLiteOpenHelper helper = null;

	// リストにて選択したHitokotoテーブルのレコードの「_id」カラム値を保持する変数の宣言
	int selectedID = -1;
	// リストにて選択した行番号を保持する変数の宣言
	int lastPosition = -1;

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

		ListView lst_Hitokoto = (ListView)findViewById(R.id.lst_Hitokoto);
		lst_Hitokoto.setOnItemClickListener(this);

		// ListViewにDBの値をセット
		this.setDBValuetoList(lst_Hitokoto);
	}



	private void setDBValuetoList(ListView lst_Hitokoto) {

		SQLiteCursor cursor = null;

		// クラスのフィールド変数がNULLなら、データベース空間オープン
		if(sdb == null) {
			helper = new MySQLiteOpenHelper(getApplicationContext());
		}
		try {
			sdb = helper.getWritableDatabase();
		} catch(SQLiteException e) {
			// 異常終了
			Log.e("ERROR", e.toString());
		}
		// MySQLiteOpenHelperにSELECT文を実行させて結果のカーソルを受け取る
		cursor = this.helper.selectHitokotoList(sdb);

		// dblayout: ListViewにさらにレイアウトを指定するもの
		int db_layout = android.R.layout.simple_list_item_activated_1;
		// from: カーソルからListViewに指定するカラムの値を指定するもの
		String[]from = {"phrase"};
		// to: Listviewの中に指定したdb_layoutに配置する、各行のview部品のid
		int[] to = new int[]{android.R.id.text1};

		// ListViewにセットするアダプターを生成
		// カーソルをもとに、fromの列から、toのViewへ値のマッピングが行われる
		SimpleCursorAdapter adapter =
				new SimpleCursorAdapter(this,db_layout,cursor,from,to,0);

		// アダプターを設定します
		lst_Hitokoto.setAdapter(adapter);

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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
