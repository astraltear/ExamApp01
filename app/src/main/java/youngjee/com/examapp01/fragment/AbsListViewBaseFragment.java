package youngjee.com.examapp01.fragment;

import android.content.Intent;
import android.widget.AbsListView;

import youngjee.com.examapp01.AuidSampleActivity;
import youngjee.com.examapp01.AuilConstants;

public class AbsListViewBaseFragment extends BaseFragment {
    protected static final String STATE_PAUSE_ON_SCROLL = "STATE_PAUSE_ON_SCROLL";
    protected static final String STATE_PAUSE_ON_FLING = "STATE_PAUSE_ON_FLING";

    protected AbsListView listView;

    protected boolean pauseOnScroll = false;
    protected boolean pauseOnFling = true;

	protected void startImagePagerActivity(int position) {
		Intent intent = new Intent(getActivity(), AuidSampleActivity.class);
		intent.putExtra(AuilConstants.Extra.FRAGMENT_INDEX, ImagePagerFragment.INDEX);
		intent.putExtra(AuilConstants.Extra.IMAGE_POSITION, position);
		startActivity(intent);
	}

    // TODO:: 나머지 부분 이해가 안되서 일단 주석처리
    /*

    @Override
	public void onResume() {
		super.onResume();
		applyScrollListener();
	}

	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		MenuItem pauseOnScrollItem = menu.findItem(R.id.item_pause_on_scroll);
		pauseOnScrollItem.setVisible(true);
		pauseOnScrollItem.setChecked(pauseOnScroll);

		MenuItem pauseOnFlingItem = menu.findItem(R.id.item_pause_on_fling);
		pauseOnFlingItem.setVisible(true);
		pauseOnFlingItem.setChecked(pauseOnFling);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.item_pause_on_scroll:
				pauseOnScroll = !pauseOnScroll;
				item.setChecked(pauseOnScroll);
				applyScrollListener();
				return true;
			case R.id.item_pause_on_fling:
				pauseOnFling = !pauseOnFling;
				item.setChecked(pauseOnFling);
				applyScrollListener();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}



	private void applyScrollListener() {
		listView.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), pauseOnScroll, pauseOnFling));
	}
     */
}
