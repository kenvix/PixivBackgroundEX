/*
 * This file is generated by jOOQ.
 */
package com.kenvix.pixiv.generated.jooq.tables;


import com.kenvix.pixiv.generated.jooq.DefaultSchema;
import com.kenvix.pixiv.generated.jooq.Keys;
import com.kenvix.pixiv.generated.jooq.tables.records.PixivRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Pixiv extends TableImpl<PixivRecord> {

    private static final long serialVersionUID = 266865374;

    /**
     * The reference instance of <code>Pixiv</code>
     */
    public static final Pixiv PIXIV = new Pixiv();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PixivRecord> getRecordType() {
        return PixivRecord.class;
    }

    /**
     * The column <code>Pixiv.ID</code>.
     */
    public final TableField<PixivRecord, Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>Pixiv.Title</code>.
     */
    public final TableField<PixivRecord, String> TITLE = createField("Title", org.jooq.impl.SQLDataType.CLOB.nullable(false).defaultValue(org.jooq.impl.DSL.field("''", org.jooq.impl.SQLDataType.CLOB)), this, "");

    /**
     * The column <code>Pixiv.Author</code>.
     */
    public final TableField<PixivRecord, String> AUTHOR = createField("Author", org.jooq.impl.SQLDataType.CLOB.nullable(false).defaultValue(org.jooq.impl.DSL.field("''", org.jooq.impl.SQLDataType.CLOB)), this, "");

    /**
     * The column <code>Pixiv.FromURL</code>.
     */
    public final TableField<PixivRecord, String> FROMURL = createField("FromURL", org.jooq.impl.SQLDataType.CLOB.nullable(false).defaultValue(org.jooq.impl.DSL.field("''", org.jooq.impl.SQLDataType.CLOB)), this, "");

    /**
     * The column <code>Pixiv.ImgRawURL</code>.
     */
    public final TableField<PixivRecord, String> IMGRAWURL = createField("ImgRawURL", org.jooq.impl.SQLDataType.CLOB.nullable(false).defaultValue(org.jooq.impl.DSL.field("''", org.jooq.impl.SQLDataType.CLOB)), this, "");

    /**
     * The column <code>Pixiv.ImageURL</code>.
     */
    public final TableField<PixivRecord, String> IMAGEURL = createField("ImageURL", org.jooq.impl.SQLDataType.CLOB.nullable(false).defaultValue(org.jooq.impl.DSL.field("''", org.jooq.impl.SQLDataType.CLOB)), this, "");

    /**
     * The column <code>Pixiv.FilePath</code>.
     */
    public final TableField<PixivRecord, String> FILEPATH = createField("FilePath", org.jooq.impl.SQLDataType.CLOB.nullable(false).defaultValue(org.jooq.impl.DSL.field("''", org.jooq.impl.SQLDataType.CLOB)), this, "");

    /**
     * The column <code>Pixiv.Status</code>.
     */
    public final TableField<PixivRecord, String> STATUS = createField("Status", org.jooq.impl.SQLDataType.CLOB.nullable(false).defaultValue(org.jooq.impl.DSL.field("0", org.jooq.impl.SQLDataType.CLOB)), this, "");

    /**
     * Create a <code>Pixiv</code> table reference
     */
    public Pixiv() {
        this(DSL.name("Pixiv"), null);
    }

    /**
     * Create an aliased <code>Pixiv</code> table reference
     */
    public Pixiv(String alias) {
        this(DSL.name(alias), PIXIV);
    }

    /**
     * Create an aliased <code>Pixiv</code> table reference
     */
    public Pixiv(Name alias) {
        this(alias, PIXIV);
    }

    private Pixiv(Name alias, Table<PixivRecord> aliased) {
        this(alias, aliased, null);
    }

    private Pixiv(Name alias, Table<PixivRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Pixiv(Table<O> child, ForeignKey<O, PixivRecord> key) {
        super(child, key, PIXIV);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<PixivRecord, Integer> getIdentity() {
        return Keys.IDENTITY_PIXIV;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<PixivRecord> getPrimaryKey() {
        return Keys.PK_PIXIV;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<PixivRecord>> getKeys() {
        return Arrays.<UniqueKey<PixivRecord>>asList(Keys.PK_PIXIV);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pixiv as(String alias) {
        return new Pixiv(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pixiv as(Name alias) {
        return new Pixiv(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Pixiv rename(String name) {
        return new Pixiv(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Pixiv rename(Name name) {
        return new Pixiv(name, null);
    }
}