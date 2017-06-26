package live.itrip.admin.bean;

import java.util.ArrayList;

/**
 * Created by 建锋 on 2017/6/26.
 */
public class AppRecyclerItemList<T> implements java.io.Serializable {
    private long total_count;
    private boolean incomplete_results;
    private ArrayList<T> items;

    public long getTotal_count() {
        return total_count;
    }

    public void setTotal_count(long total_count) {
        this.total_count = total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public ArrayList<T> getItems() {
        return items;
    }

    public void setItems(ArrayList<T> items) {
        this.items = items;
    }

    public static class RecyclerItem {
        private int id;
        private String title;
        private String desc;
        private String iamgePath;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getIamgePath() {
            return iamgePath;
        }

        public void setIamgePath(String iamgePath) {
            this.iamgePath = iamgePath;
        }

        public RecyclerItem(int id, String title, String desc, String iamgePath) {
            this.setId(id);
            this.setTitle(title);
            this.setDesc(desc);
            this.setIamgePath(iamgePath);
        }
    }
}
