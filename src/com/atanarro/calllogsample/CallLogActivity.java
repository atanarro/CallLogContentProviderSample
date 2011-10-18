/*
 * Copyright (C)  2011  Álvaro Tanarro Santamaría.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.atanarro.calllogsample;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.widget.Toast;

/**
 * @author atanarro
 */
public class CallLogActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Uri allCalls = Uri.parse("content://call_log/calls");
		Cursor c = managedQuery(allCalls, null, null, null, null);

		while (c.moveToNext()) {
			String id = c.getString(c.getColumnIndex(CallLog.Calls._ID));
			String num = c.getString(c.getColumnIndex(CallLog.Calls.NUMBER));
			int type = Integer.parseInt(c.getString(c
					.getColumnIndex(CallLog.Calls.TYPE)));

			String callLogStr = id + ": " + num;
			switch (type) {
			case CallLog.Calls.INCOMING_TYPE:
				callLogStr += " (Incoming)";
				break;
			case CallLog.Calls.OUTGOING_TYPE:
				callLogStr += " (Outgoing)";
				break;
			case CallLog.Calls.MISSED_TYPE:
				callLogStr += " (Missed)";
				break;
			}
			Toast.makeText(this, callLogStr, Toast.LENGTH_LONG).show();
		}

	}
}
