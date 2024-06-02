package sample_program;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sample_create_file {

	
	/**
	 * ファイル作成についてまとめる
	 * 
	 * ★サイトによってFileだったり、Filesを使っているけど、結局どっち使えばいいの？
	 * 基本は、Filesの方。
	 * 
	 * Java7未満は、File
	 * Java7以降は、PathとFiles
	 * (File→Path,Filesに別れたイメージ。java.nio.file)
	 * 
	 * Fileは問題があったみたいで、非推奨とのこと
	 * File ←→ Pathの変換も可能
	 * 
	 * ★サイトによってPaths.getだったり、Path.ofを使っているけど、結局どっち使えばいいの？
	 * Path.of
	 * 
	 * Path.getの後に、Path.ofが出た。
	 * 別にPath.getを使ってもいいけど、わざわざPathsをつけるのも、importするのも面倒なため
	 * 
	 * ★サイトによって読み込み、書き込みのメソッドが違う・・・
	 * ファイルサイズが小さい
	 * 　→Path write()
	 * 　→byte[] readAllBytes
	 * 
	 * ファイルサイズが大きい
	 * 　→OutputStream newOutputStream
	 * 　→InputStream newInputStream
	 * 
	 * writeと、readAllBytesはリソース開放が必要ないため、少し楽
	 * Stream系を使用する際は、try-catch-resorceを使用する
	 * 
	 * 
	 * ★参考サイト
	 * https://programming-tips.jp/archives/b/21/index.html
	 * https://teratail.com/questions/347770
	 * https://qiita.com/toastkidjp/items/5500521ff5dc0346c2b1
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		// srcと同階層
		//Path dir_path = Path.of(System.getProperty("user.dir"));
		
		Path file_path = Path.of("C:\\pleiades\\workspaces\\common_ws\\sample_program\\output_files\\create_file_type1.txt");
		//Path full_path = dir_path.resolve(file_path);
		
		String separator = System.getProperty("line.separator");
		
		try {
		// TODO:ディレクトリが存在するかも入れる
		if(!(Files.exists(file_path))) {
			Files.createFile(file_path);
		}
		
		StringBuffer buf = new StringBuffer();
		
		Date date = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
		
		buf.append(sdf1.format(date));
		buf.append(separator);
		buf.append("レコード1");
		buf.append(separator);
		buf.append("レコード2");
		buf.append(separator);
		
		
		try(OutputStream os = Files.newOutputStream(file_path, StandardOpenOption.APPEND)){
			os.write(buf.toString().getBytes());
		}catch(Exception e) {
			//System.out.println("書き込みエラー");
		}
		
		
		}catch(Exception e){
			//System.out.println("全体エラー");
		}
			

	}
	

}
