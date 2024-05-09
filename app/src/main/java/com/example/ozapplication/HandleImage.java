package com.example.ozapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class HandleImage {

    public static void UploadingImage(Bitmap bitmap, final Context context, String dir, String mid) {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference().child((dir)).child(mid);
        //StorageReference storageReference = storage.getReferenceFromUrl("gs://firstfireapp-7a8a4.appspot.com).child((dir)).child(mid);
        //decode to byte output stream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        byte[] data = outputStream.toByteArray();
        //Upload to firebase
        UploadTask uploadTask = storageRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(context, mid + " uploadTask Failure", Toast.LENGTH_SHORT).show();
                exception.printStackTrace();
            }
        });
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(context, mid + "uploadTask Success", Toast.LENGTH_SHORT).show();
                //todo: how to get the Access token (URL address) of the picture
            }
        });
    }

    //the function downloading picture from dir/mid and setting to imageView
    public static void DownloadingImage(final ImageView imageView, final Context context, String dir, String mid) {
        final long ONE_MEGABYTE = 1024 * 1024;
        StorageReference storageRef = FirebaseStorage.getInstance().getReference().child((dir)).child(mid);
        //StorageReference storageReference = storage.getReferenceFromUrl("gs://firstfireapp-7a8a4.appspot.com).child((dir)).child(mid);
        //download file as a byte array

        //Task fileDownloadTask = storageRef.getBytes(ONE_MEGABYTE);
        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Toast.makeText(context, mid + " Download Success", Toast.LENGTH_SHORT).show();
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }); //todo: add failure listener
    }
}
