/*
 * This file is generated by jOOQ.
 */
package com.kenvix.pixiv.generated.jooq.tables;


import com.kenvix.pixiv.generated.jooq.DefaultSchema;
import com.kenvix.pixiv.generated.jooq.Keys;
import com.kenvix.pixiv.generated.jooq.tables.records.ExampleRecord;

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
public class Example extends TableImpl<ExampleRecord> {

    private static final long serialVersionUID = 1418345022;

    /**
     * The reference instance of <code>Example</code>
     */
    public static final Example EXAMPLE = new Example();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ExampleRecord> getRecordType() {
        return ExampleRecord.class;
    }

    /**
     * The column <code>Example.ID</code>.
     */
    public final TableField<ExampleRecord, Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>Example.Title</code>.
     */
    public final TableField<ExampleRecord, String> TITLE = createField("Title", org.jooq.impl.SQLDataType.CLOB.nullable(false).defaultValue(org.jooq.impl.DSL.field("''", org.jooq.impl.SQLDataType.CLOB)), this, "");

    /**
     * The column <code>Example.Author</code>.
     */
    public final TableField<ExampleRecord, String> AUTHOR = createField("Author", org.jooq.impl.SQLDataType.CLOB.nullable(false).defaultValue(org.jooq.impl.DSL.field("''", org.jooq.impl.SQLDataType.CLOB)), this, "");

    /**
     * The column <code>Example.FromURL</code>.
     */
    public final TableField<ExampleRecord, String> FROMURL = createField("FromURL", org.jooq.impl.SQLDataType.CLOB.nullable(false).defaultValue(org.jooq.impl.DSL.field("''", org.jooq.impl.SQLDataType.CLOB)), this, "");

    /**
     * The column <code>Example.ImgRawURL</code>.
     */
    public final TableField<ExampleRecord, String> IMGRAWURL = createField("ImgRawURL", org.jooq.impl.SQLDataType.CLOB.nullable(false).defaultValue(org.jooq.impl.DSL.field("''", org.jooq.impl.SQLDataType.CLOB)), this, "");

    /**
     * The column <code>Example.ImageURL</code>.
     */
    public final TableField<ExampleRecord, String> IMAGEURL = createField("ImageURL", org.jooq.impl.SQLDataType.CLOB.nullable(false).defaultValue(org.jooq.impl.DSL.field("''", org.jooq.impl.SQLDataType.CLOB)), this, "");

    /**
     * The column <code>Example.FilePath</code>.
     */
    public final TableField<ExampleRecord, String> FILEPATH = createField("FilePath", org.jooq.impl.SQLDataType.CLOB.nullable(false).defaultValue(org.jooq.impl.DSL.field("''", org.jooq.impl.SQLDataType.CLOB)), this, "");

    /**
     * The column <code>Example.Status</code>.
     */
    public final TableField<ExampleRecord, String> STATUS = createField("Status", org.jooq.impl.SQLDataType.CLOB.nullable(false).defaultValue(org.jooq.impl.DSL.field("0", org.jooq.impl.SQLDataType.CLOB)), this, "");

    /**
     * Create a <code>Example</code> table reference
     */
    public Example() {
        this(DSL.name("Example"), null);
    }

    /**
     * Create an aliased <code>Example</code> table reference
     */
    public Example(String alias) {
        this(DSL.name(alias), EXAMPLE);
    }

    /**
     * Create an aliased <code>Example</code> table reference
     */
    public Example(Name alias) {
        this(alias, EXAMPLE);
    }

    private Example(Name alias, Table<ExampleRecord> aliased) {
        this(alias, aliased, null);
    }

    private Example(Name alias, Table<ExampleRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Example(Table<O> child, ForeignKey<O, ExampleRecord> key) {
        super(child, key, EXAMPLE);
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
    public Identity<ExampleRecord, Integer> getIdentity() {
        return Keys.IDENTITY_EXAMPLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ExampleRecord> getPrimaryKey() {
        return Keys.PK_EXAMPLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ExampleRecord>> getKeys() {
        return Arrays.<UniqueKey<ExampleRecord>>asList(Keys.PK_EXAMPLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Example as(String alias) {
        return new Example(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Example as(Name alias) {
        return new Example(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Example rename(String name) {
        return new Example(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Example rename(Name name) {
        return new Example(name, null);
    }
}
