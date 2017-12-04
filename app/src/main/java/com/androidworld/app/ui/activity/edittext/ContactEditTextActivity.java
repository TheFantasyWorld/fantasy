package com.androidworld.app.ui.activity.edittext;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.EditText;

import com.androidworld.app.R;
import com.androidworld.app.ui.activity.base.BaseSwipeBackActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class ContactEditTextActivity extends BaseSwipeBackActivity {

    @Bind(R.id.et_contact)
    EditText etContact;

    @Bind(R.id.rv_contact)
    RecyclerView rvContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final List<String> data = new ArrayList<>();
        data.add("I'm test data 1```");
        data.add("I'm test data 2```");
        data.add("I'm test data 3```");
        data.add("I'm test data 4```");
        data.add("I'm test data 5```");
        data.add("I'm test data 6```");
        data.add("I'm test data 7```");
        data.add("I'm test data 8```");
        data.add("I'm test data 9```");
        data.add("I'm test data 10```");
        rvContact.setLayoutManager(new LinearLayoutManager(this));
        rvContact.setAdapter(new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_contact, data) {
            @Override
            protected void convert(BaseViewHolder helper, final String item) {
                helper.setText(R.id.tv_name, item);
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addImageSpan(item);
                    }
                });
            }
        });
    }

    @Override
    protected int getSubContentViewLayoutId() {
        return R.layout.activity_contact_edittext;
    }

    /**
     * 图片
     */
    private void addImageSpan(String name) {
        SpannableString spanString = new SpannableString(" ");
        Drawable d = getResources().getDrawable(R.mipmap.ic_launcher);
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
        spanString.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        etContact.append(spanString);
    }
}
