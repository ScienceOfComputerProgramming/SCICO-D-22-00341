/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.eclipse.papyrus.gamification.rxswt;

import org.eclipse.swt.widgets.Display;

import io.reactivex.Scheduler;

public class SwtSchedulers {

	private static final class MainHolder {
		static final Scheduler DEFAULT = new DisplayScheduler(Display.getDefault());
	}

	private static final Scheduler DEFAULT_DISPLAY_THREAD = MainHolder.DEFAULT;

	/**
	 * A {@link Scheduler} which executes actions on the SWT UI thread of the
	 * default {@link Display}.
	 */
	public static Scheduler defaultDisplayThread() {
		return DEFAULT_DISPLAY_THREAD;
	}

	private SwtSchedulers() {
		throw new AssertionError("No instances.");
	}
}
