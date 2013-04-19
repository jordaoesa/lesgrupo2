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
		if(view == null){
			view = inflater.inflate(R.layout.item_anamnese_exp_list_view_child, parent, false);
			View v = null;
			if(parentsAndChildsIDS.get(groupPosition).getChildsIds().get(childPosition).contains("textView")){
				v = new TextView(context);
				((TextView)v).setText(parentsAndChildsIDS.get(groupPosition).getChildsIds().get(childPosition).replace("textView", ""));
				((TextView)v).setTextAppearance(context, android.R.attr.textAppearanceMedium);
			}else if(parentsAndChildsIDS.get(groupPosition).getChildsIds().get(childPosition).contains("radioGroup")){
				//TODO
			}else if(parentsAndChildsIDS.get(groupPosition).getChildsIds().get(childPosition).contains("editText")){
				v = new EditText(context);
				((EditText)v).setText("");
			}
			((LinearLayout)view).addView(v);
		}
		
		
		//TextView tv = (TextView) view.findViewById(R.id.list_item_text_child);
		//tv.setText(parentsAndChildsIDS.get(groupPosition).getChildsIds().get(childPosition));
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
		
		TextView tv = (TextView) view.findViewById(R.id.list_item_text_view);
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
