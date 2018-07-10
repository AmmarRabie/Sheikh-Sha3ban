package com.products.ammar.sheikhsha3ban.common.data;

import com.products.ammar.sheikhsha3ban.common.data.model.AdviceModel;
import com.products.ammar.sheikhsha3ban.common.data.model.EvaluationModel;
import com.products.ammar.sheikhsha3ban.common.data.model.PostModel;
import com.products.ammar.sheikhsha3ban.common.data.model.UserModel;

import java.util.ArrayList;

/**
 * Main entry point for accessing app data.
 * <p>
 * This interface defines the interaction with our data and data models.
 * For example this interface can be implemented with firebase model (in our case) or later
 * implemented with stored procedures. This interface is like stored procedures to our data.
 * <p>
 * Methods parameters in this class follow that they receive required data to do the operation,
 * then response with one of five callbacks (
 * {@link Insert}, <p>
 * {@link Update}, <p>
 * {@link Delete}, <p>
 * {@link Get}, <p>
 * {@link Listen})
 */
public interface DataService {


    /**
     * Get the user by its id
     *
     * @param userId   the id of the user caller want to get
     * @param callback Send the data to the caller or tell him with errors
     */
    void getUser(String userId, Get<UserModel> callback);

    /**
     * Insert new user into the data source. Note that this method doesn't authenticate the user
     * Caller should handle authentication outside this method
     *
     * @param userModel user to insert him in the data source
     * @param callback  Send the feedback to the caller or tell him with errors
     */
    void insertUser(UserModel userModel, byte[] profileImageBytes, Insert<Void> callback);

    void getAllAdvice(Get<ArrayList<AdviceModel>> callback);

    void getMorePosts(final Get<ArrayList<PostModel>> callback, int moreIndx);

    void insertPost(String userId, String body, Insert<PostModel> callback);

    void updateUserProfileImage(String userId, byte[] newImageBytes, final Update callback);

    void updateUserName(String userId, String newName, final Update callback);

    void getUserRats(String userId, Get<EvaluationModel> callback);

    void updateUserRates(String userId, ArrayList<Integer> partsPos,
                        ArrayList<Integer> quartersPos,
                        ArrayList<Integer> typesPos,
                        ArrayList<Integer> newRates,
                        Update callback);

    void updateUserRate(String userId, int partsPos,
                         int quarterPos,
                         int typePos,
                         int newRate,
                         Update callback);

    /**
     * Remove notifying callbacks with changing. After this function is called by a given listener
     * this listener will not receive {@link Listen#onDataReceived}yhhhhh even data changed
     *
     * @param listener the listener passed to any listen function from {@link DataService}
     */
    void forget(Listen listener);


    /**
     * define the general callback of listening for a value changed
     * you should properly listen and forget the listening with {@link #forget(Listen)}
     *
     * @param <D> type of the data returned on data change.
     * @see #forget(Listen)
     */
    abstract class Listen<D> {
        private final int mListenFromChange;
        private final int mListenToChange;
        private int count = 0;

        /**
         * data changing meaning
         *
         * @param listenFromChange count of data change to call onDataReceived for the first time,
         *                         default value for this is 0 meaning that it will call
         *                         onDataChanged on first time it fetches the data and
         *                         after first change, ...etc depending on the
         *                         listenToChange value and calling to forget.
         * @param listenToChange   count of data changes to call onDataReceived for the last time,
         *                         default value for this is -1 meaning that it listen forever till
         *                         {@link #forget(Listen)} is called of this listener. passing 2 while
         *                         listenFromChange is 1 meaning listen for first change and second
         *                         change only (note: without calling for first time data fetched).
         */
        public Listen(int listenFromChange, int listenToChange) {
            mListenFromChange = listenFromChange;
            mListenToChange = listenToChange;
        }

        /**
         * instantiate a new listener with default listening value
         * from first fetching the data till the listener forgotten
         */
        public Listen() {
            this(0, -1);
        }

        public final int getListenFromChange() {
            return mListenFromChange;
        }

        public final int getListenToChange() {
            return mListenToChange;
        }

        public abstract void onDataReceived(D dataSnapshot);

        /**
         * should be called each time callback handler call onDataReceived
         */
        public final void increment() {
            count++;
        }

        public final int getCount() {
            return count;
        }

        public final boolean shouldListen() {
            if (getListenToChange() == -1)
                return count >= getListenFromChange();
            return count >= getListenFromChange() && count <= getListenToChange();
        }

    }

    /**
     * define the general callback of insertion operation
     *
     * @param <fb> type of data returned on feedback (after data is inserted)
     */
    abstract class Insert<fb> {
        public abstract void onDataInserted(fb feedback);

        public void onError(String cause) {
        }
    }

    /**
     * define the general callback of deletion operation
     */
    abstract class Delete {
        public abstract void onDeleted();

        public void onError(String cause) {
        }

    }

    /**
     * define the general callback of deletion operation
     *
     * @param <D> type of data returned after fetching success
     */
    abstract class Get<D> {
        public abstract void onDataFetched(D data);

        public void onDataNotAvailable() {
        }

        public void onError(String cause) {
        }
    }

    /**
     * define the general callback of update operation
     */
    abstract class Update {
        public abstract void onUpdateSuccess();

        public void onDataNotAvailable() {
        }

        public void onError(String cause) {
        }
    }
}
