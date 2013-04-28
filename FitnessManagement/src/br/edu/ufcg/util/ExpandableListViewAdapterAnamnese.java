package br.edu.ufcg.util;

import java.util.HashMap;
import java.util.List;

import br.edu.ufcg.fachada.AnamneseFachada.Anamnese;
/*import br.edu.ufcg.fitnessmanagement.AnamneseActivity.ParentListView;*/
import br.edu.ufcg.fitnessmanagement.R;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class ExpandableListViewAdapterAnamnese /*extends BaseExpandableListAdapter*/ {
	
	/*private LayoutInflater inflater;
	private Context context;
	private List<ParentListView> parentsAndChildsIDS;
	
	public ExpandableListViewAdapterAnamnese(Context context, List<ParentListView> parentsAndChildsIDS) {
		this.context = context;
		this.parentsAndChildsIDS = parentsAndChildsIDS;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return parentsAndChildsIDS.get(groupPosition).getChildsIds().get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
		if(parentsAndChildsIDS.get(groupPosition).getChildsIds().get(childPosition).contains("radioButton")){
			view = inflater.inflate(R.layout.item_anamnese_exp_list_view_child_radio_button, null);
		}else if(parentsAndChildsIDS.get(groupPosition).getChildsIds().get(childPosition).contains("editText")){
			view = inflater.inflate(R.layout.item_anamnese_exp_list_view_child_edit_text, null);
		}
		
		if(parentsAndChildsIDS.get(groupPosition).getChildsIds().get(childPosition).contains("radioButton")){
		 	TextView tv = (TextView) view.findViewById(R.id.list_item_textView_child_radio_button);
		 	tv.setText(parentsAndChildsIDS.get(groupPosition).getChildsIds().get(childPosition).replace("radioButton", ""));
			RadioButton rb = (RadioButton) view.findViewById(R.id.list_item_radioButton_child_radio_button);
			rb.setText(parentsAndChildsIDS.get(groupPosition).getChildsIds().get(childPosition).replace("radioButton", ""));
		}else if(parentsAndChildsIDS.get(groupPosition).getChildsIds().get(childPosition).contains("editText")){
			TextView tv = (TextView) view.findViewById(R.id.list_item_textView_child_edit_text);
			tv.setText(parentsAndChildsIDS.get(groupPosition).getChildsIds().get(childPosition).replace("editText", ""));
			EditText et = (EditText) view.findViewById(R.id.list_item_editText_child_edit_text);
			et.setText("");
		}
		
		return view;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return parentsAndChildsIDS.get(groupPosition).getChildsIds().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return parentsAndChildsIDS.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return parentsAndChildsIDS.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
		if(view == null){
			view = inflater.inflate(R.layout.item_anamnese_exp_list_view_parent, parent, false);
		}
		
		TextView tv = (TextView) view.findViewById(R.id.list_item_text_view_parent);
		tv.setText(parentsAndChildsIDS.get(groupPosition).getId().replace("textView", ""));
		return view;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}*/

}
