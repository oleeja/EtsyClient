package com.kitsyambocka.etsyclient.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.kitsyambocka.etsyclient.models.goods.MainImage;
import com.kitsyambocka.etsyclient.models.goods.ResultGoods;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Developer on 11.12.2016.
 */

public class DBManager {

    private static final String ID ="_id";
    private static final String TITLE ="title";
    private static final String DESCTIPTION ="description";
    private static final String PRICE ="price";
    private static final String CURRENCY ="currency";
    private static final String SMALL_PIC ="smallpic";
    private static final String BIG_PIC ="bigpic";
    private static final String GOODS ="goods";

    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    public DBManager (Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLiteException {
        if (database == null) {
            database = dbHelper.getWritableDatabase();
        }

    }

    public void close() throws SQLiteException {
        if (database != null && database.isOpen()) {
            database.close();
            dbHelper.close();
            database = null;
        }
    }

    public void addGoods(ResultGoods goods) {
        ContentValues values = new ContentValues();

        values.put(ID, goods.getListingId());
        values.put(TITLE, goods.getTitle());
        values.put(DESCTIPTION, goods.getDescription());
        values.put(PRICE, goods.getPrice());
        values.put(CURRENCY, goods.getCurrencyCode());
        values.put(SMALL_PIC, goods.getMainImage().getUrl_170x135());
        values.put(BIG_PIC, goods.getMainImage().getUrl_570xN());
        database.insert(GOODS, null, values);

    }

    public List<ResultGoods> getGoods() {
        List<ResultGoods> goodsList = new ArrayList<>();

        Cursor cursor = database.query(GOODS, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {

            do {
                ResultGoods goods = new ResultGoods();
                MainImage images = new MainImage();

                goods.setListingId(cursor.getLong(cursor.getColumnIndex(ID)));
                images.setUrl_170x135(cursor.getString(cursor.getColumnIndex(SMALL_PIC)));
                goods.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                goods.setMainImage(images);

                goodsList.add(goods);
            } while (cursor.moveToNext());
            return goodsList;
        }
        return null;
    }

    public ResultGoods getGoodsById(long id) {

        Cursor cursor = database.query(GOODS, new String[]{TITLE, DESCTIPTION, PRICE, CURRENCY, BIG_PIC, ID}, ID+"=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor.moveToFirst()) {
            ResultGoods goods = new ResultGoods();
            MainImage images = new MainImage();

            goods.setListingId(cursor.getLong(cursor.getColumnIndex(ID)));
            images.setUrl_570xN(cursor.getString(cursor.getColumnIndex(BIG_PIC)));
            goods.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
            goods.setDescription(cursor.getString(cursor.getColumnIndex(DESCTIPTION)));
            goods.setPrice(cursor.getString(cursor.getColumnIndex(PRICE)));
            goods.setCurrencyCode(cursor.getString(cursor.getColumnIndex(CURRENCY)));
            goods.setMainImage(images);
            return goods;
        }

        return null;

    }

    public void deleteGoodsById(long id) {

        database.delete(GOODS, ID+"=?", new String[]{String.valueOf(id)});

    }
}
