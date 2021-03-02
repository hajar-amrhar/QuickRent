package com.example.findrent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class CamGaler extends AppCompatActivity {

    Fragment selectedFragment;
    ProgressBar PB;


    private static final int CAMERA_PERM_CODE = 101;
    private static final int GALLERY_PERM_CODE = 106;
    private static final int CAMERA_REQUEST_CODE = 102;
    private static final int GALERY_REQUEST_CODE = 105;

    int i=0;


    //private Uri


    private String uri1,uri2,uri3,uri4;


    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    //mDatabase = FirebaseDatabase.getInstance();
    // mDb = mDatabase.getReference();
    FirebaseUser user = mAuth.getCurrentUser();

    Button Camerabtn, GalerieBtn, suivantbtn,suiv;
    ImageView selectedimage;
    private Bitmap imagebitmap;
    StorageReference storageReference;
    private DatabaseReference root= FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).child("imageInst");
    private Uri selectedimageuri;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam_galer);






        selectedimage = findViewById(R.id.imageview);
        Camerabtn = findViewById(R.id.buttoncamera);
        GalerieBtn = findViewById(R.id.buttongalerie);
        storageReference = FirebaseStorage.getInstance().getReference();
//        PB.setVisibility(View.INVISIBLE);
        suivantbtn= findViewById(R.id.suivantid);
        suiv= findViewById(R.id.suiv);
        suiv.setOnClickListener(add);
        Camerabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askCameraPermissions();
            }
        });
        GalerieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(CamGaler.this, "Vous avez choisit: la galerie", Toast.LENGTH_SHORT).show();
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(gallery, "choisissez une photo"), GALERY_REQUEST_CODE);

            }


            //send uri to imageIst

        });
           /*  Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "choisit une photo"), GALERY_REQUEST_CODE);
          startActivityForResult(intent,GALERY_REQUEST_CODE);*/
        suivantbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedimage!=null){
                    UploadToFirebase(selectedimageuri);
                }
                else{
                    Toast.makeText(CamGaler.this, "Choisissez une photo s'il vous plait", Toast.LENGTH_SHORT).show();

                }
                if (imagebitmap!=null){
                    upload();
                }
            }



        });

        //send uri to imageIst



    }





    private void askCameraPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        } else {
            // OpenCamera();
            OpenCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERM_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                OpenCamera();
            } else {
                Toast.makeText(CamGaler.this, "L'autorisation de la caméra est requise pour utiliser la caméra", Toast.LENGTH_SHORT).show();

            }

        }
        if (requestCode == GALLERY_PERM_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickImagefromGallery();

            }

        } else {
            Toast.makeText(CamGaler.this, "L'autorisation de la galerie est requise pour utiliser la caméra", Toast.LENGTH_SHORT).show();

        }

    }

    public void pickImagefromGallery() {

        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALERY_REQUEST_CODE);

    }

    private void OpenCamera() {
        Toast.makeText(this, "Demande d'ouverture de la caméra", Toast.LENGTH_SHORT).show();
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA_REQUEST_CODE);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {
            // Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            imagebitmap = (Bitmap) data.getExtras().get("data");
            selectedimage.setImageBitmap(imagebitmap);
        } else if (requestCode == GALERY_REQUEST_CODE && null != data) {
            selectedimageuri = (Uri) data.getData();
            selectedimage.setImageURI(selectedimageuri);
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + Environment.getExternalStorageDirectory())));

        }
    }
    private void UploadToFirebase(Uri uri){
        StorageReference fileRef = storageReference.child(System.currentTimeMillis()+'.'+ getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override

                    public void onSuccess(Uri uri) {

                        Toast.makeText(CamGaler.this, "image ajoutée", Toast.LENGTH_SHORT).show();

                        String ModelId= root.push().getKey();
                        //  root.setValue(model);

                        if(i==0){
                            uri1=uri.toString();

                           // PB.setVisibility(View.INVISIBLE);
                        }
                        if(i==1){
                            uri2=uri.toString();
                           // PB.setVisibility(View.INVISIBLE);

                        }
                        if(i==2){
                            uri3=uri.toString();
                           // PB.setVisibility(View.INVISIBLE);

                        }
                        if(i==3){
                            uri4=uri.toString();
                           // PB.setVisibility(View.INVISIBLE);

                            i=0;

                            Model model = new Model(uri1,uri2,uri3,uri4);
                            root.setValue(model);
                            Toast.makeText(CamGaler.this, "Le téléchargement a réussi", Toast.LENGTH_SHORT).show();








                        }
                        else {

                            Toast.makeText(CamGaler.this, "vous ne pouver pas ajouter plus que 4 photo ", Toast.LENGTH_SHORT).show();

                        }
                        i++;
                       // Toast.makeText(CamGaler.this, i, Toast.LENGTH_SHORT).show();


                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
//              progressBar.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //    progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(CamGaler.this, "Le téléchargement a echoué", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private String getFileExtension(Uri mUri){
        ContentResolver cr = getContentResolver() ;
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }
    public void upload(){

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imagebitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        final String random = UUID.randomUUID().toString();
        StorageReference fileRef = storageReference.child("image/"+ random);

        byte[] b = stream.toByteArray();

        fileRef.putBytes(b)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // p.setVisibility(View.GONE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                        taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Uri downloadUri = uri;
                            }
                        });

                        Toast.makeText(CamGaler.this, "Photo Uploaded", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //  p.setVisibility(View.GONE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                        Toast.makeText(CamGaler.this, "Upload Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private View.OnClickListener add = new View.OnClickListener() {

        @Override
        public void onClick(View v) {


            if (i==4){
                Toast.makeText(CamGaler.this, "vous devez ajouter 4 images", Toast.LENGTH_SHORT).show();


            }
            else {


                startActivity(new Intent(CamGaler.this, add.class));
           /*FragmentTransaction f= getSupportFragmentManager().beginTransaction();
           f.replace(R.id.cam_space,new addFragment()).commit();

            */

        }


        }
    };

}