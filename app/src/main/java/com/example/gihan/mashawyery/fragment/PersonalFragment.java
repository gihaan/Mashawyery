package com.example.gihan.mashawyery.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gihan.mashawyery.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import id.zelory.compressor.Compressor;

import static android.app.Activity.RESULT_OK;


public class PersonalFragment extends Fragment {

    TextView name, email, phone, password, verified;
    ImageView changePhoto;
    CircleImageView image;

    private ProgressDialog mProgrss;
    private DatabaseReference mUserDataBase;
    private StorageReference mImageStorage;
    private FirebaseUser currentUser;

    private static final int GALLERY_PICK = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_personal, container, false);

        changePhoto = (ImageView) v.findViewById(R.id.personal_fragment_changr_photo);
        image = (CircleImageView) v.findViewById(R.id.personal_fragment_image);
        name = (TextView) v.findViewById(R.id.personal_fragment_tv_name);
        email = (TextView) v.findViewById(R.id.personal_fragment_tv_email);
        phone = (TextView) v.findViewById(R.id.personal_fragment_tv_phone);
        password = (TextView) v.findViewById(R.id.personal_fragment_tv_password);
        verified = (TextView) v.findViewById(R.id.personal_fragment_tv_verified);

        //Firebase
        mProgrss = new ProgressDialog(getContext());
        mImageStorage = FirebaseStorage.getInstance().getReference();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = currentUser.getUid();
        mUserDataBase = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
        mUserDataBase.keepSynced(true);

        ///get data from firebase and put on text view
        mUserDataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String namee = dataSnapshot.child("name").getValue().toString();
                String emaile = dataSnapshot.child("email").getValue().toString();
                String phonee = dataSnapshot.child("phone").getValue().toString();
                String passworde = dataSnapshot.child("password").getValue().toString();
                String verifiede = dataSnapshot.child("verivied").getValue().toString();
                String imagee = dataSnapshot.child("image").getValue().toString();


                name.setText(namee);
                email.setText(emaile);
                phone.setText(phonee);
                password.setText(passworde);
                if (verifiede != "not") {
                    verified.setText("not Verified");
                    verified.setTextColor(Color.RED);
                } else {
                    verified.setText("Verified");
                    verified.setTextColor(Color.GREEN);
                }

                Picasso.with(getActivity()).load(imagee).placeholder(R.drawable.man).into(image);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        changePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(getActivity());

            }
        });

        //edit  persoal data l
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editName();
            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePhone();
            }
        });
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePassword();
            }
        });


        return v;
    }


    private void updatePassword() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View edit_layout = inflater.inflate(R.layout.edit_password, null);

        final EditText etPassword = edit_layout.findViewById(R.id.edit_password_et_new_password);

        builder.setView(edit_layout);

        //set potive && negative button
        builder.setPositiveButton("Save changes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //checkValidation
                final String newPassword = etPassword.getText().toString();
                if (!TextUtils.isEmpty(newPassword)) {
                    mUserDataBase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            dataSnapshot.getRef().child("password").setValue(newPassword);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    });

                }
                dialog.dismiss();
            }
        });


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        builder.show();

    }

    private void updatePhone() {


        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View edit_layout = inflater.inflate(R.layout.edit_phone, null);

        final EditText etPhone = edit_layout.findViewById(R.id.edit_phone_et_new_phone);

        builder.setView(edit_layout);

        //set potive && negative button
        builder.setPositiveButton("Save changes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //checkValidation
                final String newPhone = etPhone.getText().toString();
                if (!TextUtils.isEmpty(newPhone) && newPhone.length() == 11) {
                    mUserDataBase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            dataSnapshot.getRef().child("phone").setValue(newPhone);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    });

                }
                dialog.dismiss();
            }
        });


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        builder.show();


    }

    private void editName() {


        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View edit_layout = inflater.inflate(R.layout.edit_name_layout, null);

        final EditText etName = edit_layout.findViewById(R.id.edit_name_et_new_name);

        builder.setView(edit_layout);

        //set potive && negative button
        builder.setPositiveButton("Save changes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //checkValidation
                final String userNAme = etName.getText().toString();
                if (!TextUtils.isEmpty(userNAme)) {
                    mUserDataBase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            dataSnapshot.getRef().child("name").setValue(userNAme);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    });

                }
                dialog.dismiss();
            }
        });


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        builder.show();


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == GALLERY_PICK && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();

            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON).
                    setAspectRatio(1, 1)
                    .start(getActivity());
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                mProgrss.setMessage("wait to upload image ");
                mProgrss.setTitle("upload image");
                mProgrss.show();

                final Uri resultUri = result.getUri();
                String currentUserId = currentUser.getUid();

                File thump_filePath = new File(resultUri.getPath());
                try {
                    Bitmap thump_pitmap = new Compressor(getContext())
                            .setMaxHeight(200)
                            .setMaxWidth(200).setQuality(75)
                            .compressToBitmap(thump_filePath);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    thump_pitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    final byte[] thump_byte = baos.toByteArray();


                    StorageReference filePath = mImageStorage.child("image").child(currentUserId + ".jpg");
                    final StorageReference thump_file = mImageStorage.child("thump_up").child(currentUserId + ".jpg");

                    filePath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getActivity(), "work ", Toast.LENGTH_SHORT).show();


                                final String download_uri = task.getResult().getDownloadUrl().toString();
                                UploadTask uploadTask = thump_file.putBytes(thump_byte);
                                uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> thump_task) {
                                        String thump_downloadUrl = thump_task.getResult().getDownloadUrl().toString();

                                        if (thump_task.isSuccessful()) {
                                            Map update_hashmap = new HashMap();
                                            update_hashmap.put("image", download_uri);
                                            update_hashmap.put("themp_up", thump_downloadUrl);


                                            mUserDataBase.updateChildren(update_hashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(getActivity(), "Sucess loading thump file", Toast.LENGTH_SHORT).show();
                                                        mProgrss.dismiss();

                                                    }

                                                }
                                            });
                                        } else {
                                            Toast.makeText(getActivity(), "Error when loading thump File", Toast.LENGTH_SHORT).show();
                                            mProgrss.dismiss();
                                        }
                                    }
                                });


                            } else {
                                Toast.makeText(getActivity(), "Error when loading file", Toast.LENGTH_SHORT).show();
                                mProgrss.dismiss();

                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }


}
