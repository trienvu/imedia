package com.gtotek.imedia.constant;

import android.content.Context;

import com.gtotek.imedia.customview.DialogCustomView;
import com.gtotek.imedia.receiver.Network;

public class Constant {
	public static final String TITLE_DIALOG_NETWORK = "Lỗi mạng !!!!";
	public static final String MESSAGE_DIALOG_NETWORK = "Vui lòng kiểm tra lại kết nối mạng";
	public static final String BUTTONPOSITIVE_DIALOG_NETWORK = "Đồng ý";
	public static final String BUTTONNEGATIVE_DIALOG_NETWORK = "Thử lại";
	public static final int SPLASH_DISPLAY_LENGTH = 5000;

	// URL
	public static final String CHANNEL_URL1 = "http://eobiet.com/tv_channels/loadChannelByCategory?category";
	public static final String CATEGORY_URL = "http://eobiet.com/tv_categories/loadCategory";

	// Category tag
	public static final String TAG_CATEGORY = "TvCategory";
	public static final String TAG_CATE_ID = "id";
	public static final String TAG_CATE_NAME = "name";
	public static final String TAG_CATE_ICON = "image";
	public static final String TAG_CATE_DATA = "data";

	// Channel tag
	public static final String TAG_CHANNELS = "data";
	public static final String TAG__CHANNEL_ID = "id";
	public static final String TAG_CHANNEL_NAME = "name";
	public static final String TAG_LOGO_URL = "image";
	public static final String TAG_CHANNEL_DESCRIPTION = "desc";
	public static final String TAG_CHANNEL_URL = "url";

	// Server Link tag
	public static final String TAG_SERVER = "data";
	public static final String TAG_SERVER_LINK = "link";

	// Current play
	public static String streamingUrl = "http://112.197.2.154:1935/colive/vtv_1_2/playlist.m3u8";

	public static final String AUT_KEY = "cdd1c092d71a50e0cf5f37a2523ed43e";
	public static final String GROUP_CHANEL_URL = "http://kids.hatkara.com/api/groupchannel?aut_key="
			+ AUT_KEY;
	public static final String CHANNEL_URL = "http://kids.hatkara.com/api/channel?aut_key="
			+ AUT_KEY + "&id_groupchannel=";
	public static final String CHANEL_LINK_URL = "http://kids.hatkara.com/api/link?aut_key="
			+ AUT_KEY + "&id_channel=";
	
}
