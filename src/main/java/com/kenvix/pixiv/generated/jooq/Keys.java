/*
 * This file is generated by jOOQ.
 */
package com.kenvix.pixiv.generated.jooq;


import com.kenvix.pixiv.generated.jooq.tables.Example;
import com.kenvix.pixiv.generated.jooq.tables.Pixiv;
import com.kenvix.pixiv.generated.jooq.tables._PixivOld_20181028;
import com.kenvix.pixiv.generated.jooq.tables.records.ExampleRecord;
import com.kenvix.pixiv.generated.jooq.tables.records.PixivRecord;
import com.kenvix.pixiv.generated.jooq.tables.records._PixivOld_20181028Record;

import javax.annotation.Generated;

import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code></code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<ExampleRecord, Integer> IDENTITY_EXAMPLE = Identities0.IDENTITY_EXAMPLE;
    public static final Identity<PixivRecord, Integer> IDENTITY_PIXIV = Identities0.IDENTITY_PIXIV;
    public static final Identity<_PixivOld_20181028Record, Integer> IDENTITY__PIXIV_OLD_20181028 = Identities0.IDENTITY__PIXIV_OLD_20181028;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ExampleRecord> PK_EXAMPLE = UniqueKeys0.PK_EXAMPLE;
    public static final UniqueKey<PixivRecord> PK_PIXIV = UniqueKeys0.PK_PIXIV;
    public static final UniqueKey<_PixivOld_20181028Record> PK__PIXIV_OLD_20181028 = UniqueKeys0.PK__PIXIV_OLD_20181028;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<ExampleRecord, Integer> IDENTITY_EXAMPLE = Internal.createIdentity(Example.EXAMPLE, Example.EXAMPLE.ID);
        public static Identity<PixivRecord, Integer> IDENTITY_PIXIV = Internal.createIdentity(Pixiv.PIXIV, Pixiv.PIXIV.ID);
        public static Identity<_PixivOld_20181028Record, Integer> IDENTITY__PIXIV_OLD_20181028 = Internal.createIdentity(_PixivOld_20181028._PIXIV_OLD_20181028, _PixivOld_20181028._PIXIV_OLD_20181028.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<ExampleRecord> PK_EXAMPLE = Internal.createUniqueKey(Example.EXAMPLE, "pk_Example", Example.EXAMPLE.ID);
        public static final UniqueKey<PixivRecord> PK_PIXIV = Internal.createUniqueKey(Pixiv.PIXIV, "pk_Pixiv", Pixiv.PIXIV.ID);
        public static final UniqueKey<_PixivOld_20181028Record> PK__PIXIV_OLD_20181028 = Internal.createUniqueKey(_PixivOld_20181028._PIXIV_OLD_20181028, "pk__Pixiv_old_20181028", _PixivOld_20181028._PIXIV_OLD_20181028.ID);
    }
}
