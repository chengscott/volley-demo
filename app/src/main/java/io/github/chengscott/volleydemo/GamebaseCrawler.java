package io.github.chengscott.volleydemo;

import java.util.ArrayList;
import java.util.Collections;

public class GamebaseCrawler implements AmazeCrawler {
    private ArrayList<String> mIndex;
    private static int mCount = 0;

    public GamebaseCrawler() {
        mIndex = new ArrayList<>();
        for (int i = 1; i < 4000; ++i) {
            mIndex.add(String.format("%04d", i));
        }
    }

    @Override
    public ArrayList<String> getUrls(int count) {
        mCount = count;
        Collections.shuffle(mIndex);
        return generateUrlsByIndex();
    }

    private ArrayList<String> generateUrlsByIndex() {
        ArrayList<String> urls = new ArrayList<>();
        for (int i = 0; i < mCount; ++i) {
            urls.add(new StringBuffer("http://i.gbc.tw/gb_img/s100x100/299")
                    .append(mIndex.get(i))
                    .append(".jpg")
                    .toString());
        }
        return urls;
    }
}
