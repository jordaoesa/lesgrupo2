package br.edu.ufcg.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import br.edu.ufcg.fachada.ImagemFachada;
import br.edu.ufcg.fachada.ImagemFachada.Thumbnail;
import br.edu.ufcg.fitnessmanagement.R;

import android.R.drawable;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapterGallery extends BaseAdapter {

	private ContentResolver contentResolver;
	private Context context;
	private List<Thumbnail> thumbnails;
	
	public ImageAdapterGallery(ContentResolver contentResolver, Context context, List<Thumbnail> thumbnails) {
		this.contentResolver = contentResolver;
		this.context = context;
		this.thumbnails = thumbnails;
		thumbnails.add(FitnessManagementSingleton.getImagemFachadaInstance().new Thumbnail(-1, -1, String.valueOf(android.R.drawable.ic_menu_gallery)));
		
//		if(thumbnails.size() == 0){
//		this.thumbnails.add(FitnessManagementSingleton.getImagemFachadaInstance().new Thumbnail(-1, -1, "/mnt/sdcard/.FitnessManagement/Fotos/Thumbnails/br.ufcg.edu.thumbnail_Thu-Mar-14-00-41-25-BRT-2013.jpg"));
//			imagens = new Integer[10];
//			for(int i=0; i<10; i++){
//				imagens[i] = android.R.drawable.gallery_thumb;
//			}
//		}
	}
	
	@Override
	public int getCount() {
		return thumbnails.size();
	}

	@Override
	public Object getItem(int position) {
		return thumbnails.get(position);
	}

	@Override
	public long getItemId(int position) {
		return thumbnails.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
//		ImageView itemGallery = new ImageView(context);
//		
//		if(getCount() > 0){
//			try {
//				Uri uri = Uri.fromFile(new File(caminhosImagens.get(position)));
//				itemGallery.setImageBitmap(Media.getBitmap(contentResolver, uri));
//			} catch (Exception e) {
//				itemGallery.setImageResource(R.drawable.ic_launcher);
//			}
//			itemGallery.setLayoutParams(new Gallery.LayoutParams(100, 100));
//			itemGallery.setScaleType(ImageView.ScaleType.FIT_CENTER);
//			itemGallery.setBackgroundResource(drawable.ic_menu_gallery);
//		}else{
//			System.out.println("--- colocando bitmaps");
//			itemGallery.setImageBitmap(imageBitmaps[position]);
//			itemGallery.setLayoutParams(new Gallery.LayoutParams(100, 100));
//			itemGallery.setScaleType(ImageView.ScaleType.FIT_CENTER);
//			itemGallery.setBackgroundResource(R.drawable.ic_launcher);
//		}
//		return itemGallery;

		ImageView imageView = new ImageView(context);
//		if(thumbnails.size() == 0){
//			imageView.setImageResource(imagens[position]);
//		}else{
			try {
				Uri uri = Uri.fromFile(new File(thumbnails.get(position).getCaminhoThumbnail()));
				imageView.setImageBitmap(Media.getBitmap(contentResolver, uri));
			} catch (Exception e) {
				imageView.setImageResource(Integer.parseInt(thumbnails.get(position).getCaminhoThumbnail()));
				System.out.println(">>> " + e.getMessage());
			}
//		}
        imageView.setLayoutParams(new Gallery.LayoutParams(200, 200));
        //imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        //imageView.setBackgroundResource(R.drawable.ic_launcher);
        return imageView;
	}

}
