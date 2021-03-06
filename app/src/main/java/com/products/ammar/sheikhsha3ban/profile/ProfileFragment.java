package com.products.ammar.sheikhsha3ban.profile;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.products.ammar.sheikhsha3ban.R;
import com.products.ammar.sheikhsha3ban.common.data.model.UserModel;
import com.products.ammar.sheikhsha3ban.common.util.ProfileImageUtil;

import java.io.ByteArrayOutputStream;

import es.dmoral.toasty.Toasty;

/**
 * Created by AmmarRabie on 08/03/2018.
 */

public class ProfileFragment extends Fragment implements ProfileContract.Views {

    private static final String TAG = "EvaluationFragment";

    private static final int GALLERY_REQUEST = 1254;
    private static final int CAMERA_REQUEST = 546;

    private ProfileContract.Actions mAction;

    private EditText mNameView;
    private EditText mPhoneView;
    private Button mEditPhoneView;
    private TextView mEmailView;
    private Button mChangePassView;
    private Button mEditNameView;
    private Button mSignOutView;
    private ImageView mProfileImageView;
    private ProgressDialog progressIndicatorView;

    private AlertDialog changePasswordDialog;

    private boolean mEditState = false;
    private boolean mEditPhoneState = false;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void setPresenter(ProfileContract.Actions presenter) {
        mAction = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_profile, container, false);

        mNameView = root.findViewById(R.id.profileFrag_name);
        mEmailView = root.findViewById(R.id.profileFrag_email);
        mChangePassView = root.findViewById(R.id.profileFrag_changePassword);
        mEditNameView = root.findViewById(R.id.profileFrag_editName);
        mSignOutView = root.findViewById(R.id.profileFrag_signOut);
        mProfileImageView = root.findViewById(R.id.profileFrag_profileImage);
        mPhoneView = root.findViewById(R.id.profileFrag_phone);
        mEditPhoneView = root.findViewById(R.id.profileFrag_editPhone);

        mSignOutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAction.signOut();
            }
        });

        mEditNameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditState) {
                    mAction.changeName(mNameView.getText().toString());
                } else {
                    mEditState = true;
                    mEditNameView.setBackground(getResources().getDrawable(android.R.drawable.ic_menu_save));
                    mNameView.setEnabled(true);
                    mNameView.selectAll();
                }
            }
        });

        mEditPhoneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditPhoneState) {
                    mAction.changePhone(mPhoneView.getText().toString());
                } else {
                    mEditPhoneState = true;
                    mEditPhoneView.setBackground(getResources().getDrawable(android.R.drawable.ic_menu_save));
                    mPhoneView.setEnabled(true);
                    mPhoneView.selectAll();
                }
            }
        });

        mProfileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] selections = {
                        "Camera", "Gallery", "Set Random profile"
                };

                AlertDialog.Builder selectionDialogBuilder = new AlertDialog.Builder(getContext());
                selectionDialogBuilder.setTitle("Set new image from");
                selectionDialogBuilder.setItems(selections, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0: // Camera
                                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                                break;
                            case 1: // Gallery
                                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(galleryIntent, GALLERY_REQUEST);
                                break;
                            case 2: // random
                                changeImage(ProfileImageUtil.createRandomImage(getContext(), 250, 250));
                                break;
                        }
                    }
                });
                selectionDialogBuilder.show();
            }
        });

        final AlertDialog.Builder changePasswordDialogBuilder = new AlertDialog.Builder(getActivity());
        changePasswordDialogBuilder.setTitle(getString(R.string.dTitle_changePass));
        final View rootView = getLayoutInflater().inflate(R.layout.dialog_change_password, null);
        changePasswordDialogBuilder.setView(rootView);
        changePasswordDialogBuilder.setPositiveButton(getString(R.string.dAction_change),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String oldPass = ((EditText) rootView.findViewById(R.id.changePasswordDialog_oldPass)).getText().toString();
                        String newPass = ((EditText) rootView.findViewById(R.id.changePasswordDialog_pass)).getText().toString();
                        String confirmPass = ((EditText) rootView.findViewById(R.id.changePasswordDialog_confirmPass)).getText().toString();
                        mAction.changePassword(oldPass, newPass, confirmPass);
                        // clear the passwords
                        ((EditText) rootView.findViewById(R.id.changePasswordDialog_oldPass)).getText().clear();
                        ((EditText) rootView.findViewById(R.id.changePasswordDialog_pass)).getText().clear();
                        ((EditText) rootView.findViewById(R.id.changePasswordDialog_confirmPass)).getText().clear();
                    }
                });

        changePasswordDialogBuilder.setNegativeButton(getString(R.string.dAction_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((EditText) rootView.findViewById(R.id.changePasswordDialog_oldPass)).getText().clear();
                ((EditText) rootView.findViewById(R.id.changePasswordDialog_pass)).getText().clear();
                ((EditText) rootView.findViewById(R.id.changePasswordDialog_confirmPass)).getText().clear();
                dialog.cancel();
            }
        });

        changePasswordDialog = changePasswordDialogBuilder.create();

        mChangePassView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePasswordDialog.show();
            }
        });

        if (savedInstanceState == null)
            mAction.start();
        return root;
    }

    private void changeImage(Bitmap newBitmap) {
        newBitmap = Bitmap.createScaledBitmap(newBitmap, 250, 250, true); // scale it first
        mProfileImageView.setImageBitmap(newBitmap);

        // convert to bytes
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        newBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] newImageBytes = byteArrayOutputStream.toByteArray();

        mAction.changeProfileImage(newImageBytes);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if (resultCode != Activity.RESULT_OK)
            return;
        switch (requestCode) {
            case CAMERA_REQUEST:
                Bitmap photo = (Bitmap) imageReturnedIntent.getExtras().get("data");
                if (photo == null) {
                    Log.e(TAG, "onActivityResult: data can't be casted to Bitmap");
                    return;
                }
                changeImage(photo);
                break;
            case GALLERY_REQUEST:
                Uri selectedImage = imageReturnedIntent.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String filePath = cursor.getString(columnIndex);
                cursor.close();

                Bitmap selectedProfileImg = BitmapFactory.decodeFile(filePath);

                changeImage(selectedProfileImg);
                break;
        }
    }

    @Override
    public void showOnChangeNameSuccess() {
        Toast.makeText(getContext(), getString(R.string.mes_nameChanged), Toast.LENGTH_SHORT).show();
        mEditState = false;
        mEditNameView.setBackground(getResources().getDrawable(android.R.drawable.ic_menu_edit));
        mNameView.setEnabled(false);
    }

    @Override
    public void showOnChangePhoneSuccess() {
        Toast.makeText(getContext(), getString(R.string.mes_phoneChanged), Toast.LENGTH_SHORT).show();
        mEditPhoneState = false;
        mEditPhoneView.setBackground(getResources().getDrawable(android.R.drawable.ic_menu_edit));
        mPhoneView.setEnabled(false);
    }

    @Override
    public void showOnChangePassSuccess() {
        Toasty.success(getContext(),
                getString(R.string.mes_passwordChanged), Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void showOnSignOutSuccess() {
        // ends this activity
        if (getActivity() != null) {
            getActivity().setResult(ProfileActivity.RESULT_SIGN_OUT);
            getActivity().finish();
        }
    }

    @Override
    public void showUserInfo(UserModel user) {
        mNameView.setText(user.getName());
        mEmailView.setText(user.getEmail());
        mPhoneView.setText(user.getPhone());
        if (user.getProfileImage() == null) // do nothing if the user is and old user with no image
            return;
        ProfileImageUtil.setProfileImage(user.getProfileImage(), mProfileImageView, 250);
    }

    @Override
    public void showErrorMessage(String cause) {
        Toasty.error(getContext(), cause, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void showProgressIndicator(String progressWorkMessage) {
        if (progressIndicatorView == null)
            progressIndicatorView = ProgressDialog.show(getContext(),
                    null, progressWorkMessage
                    , true, false);
        else {
            progressIndicatorView.setMessage(progressWorkMessage);
            progressIndicatorView.show();
        }
    }

    @Override
    public void hideProgressIndicator() {
        if (progressIndicatorView != null)
            progressIndicatorView.dismiss();
    }
}