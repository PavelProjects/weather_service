// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: auth.proto

package ru.pobopo.weather.grpc;

/**
 * Protobuf type {@code ru.pobopo.weather.grpc.Credits}
 */
public  final class Credits extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ru.pobopo.weather.grpc.Credits)
    CreditsOrBuilder {
  // Use Credits.newBuilder() to construct.
  private Credits(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Credits() {
    login_ = "";
    password_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private Credits(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            login_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            password_ = s;
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return ru.pobopo.weather.grpc.Auth.internal_static_ru_pobopo_weather_grpc_Credits_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return ru.pobopo.weather.grpc.Auth.internal_static_ru_pobopo_weather_grpc_Credits_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            ru.pobopo.weather.grpc.Credits.class, ru.pobopo.weather.grpc.Credits.Builder.class);
  }

  public static final int LOGIN_FIELD_NUMBER = 1;
  private volatile java.lang.Object login_;
  /**
   * <code>string login = 1;</code>
   */
  public java.lang.String getLogin() {
    java.lang.Object ref = login_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      login_ = s;
      return s;
    }
  }
  /**
   * <code>string login = 1;</code>
   */
  public com.google.protobuf.ByteString
      getLoginBytes() {
    java.lang.Object ref = login_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      login_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PASSWORD_FIELD_NUMBER = 2;
  private volatile java.lang.Object password_;
  /**
   * <code>string password = 2;</code>
   */
  public java.lang.String getPassword() {
    java.lang.Object ref = password_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      password_ = s;
      return s;
    }
  }
  /**
   * <code>string password = 2;</code>
   */
  public com.google.protobuf.ByteString
      getPasswordBytes() {
    java.lang.Object ref = password_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      password_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getLoginBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, login_);
    }
    if (!getPasswordBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, password_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getLoginBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, login_);
    }
    if (!getPasswordBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, password_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof ru.pobopo.weather.grpc.Credits)) {
      return super.equals(obj);
    }
    ru.pobopo.weather.grpc.Credits other = (ru.pobopo.weather.grpc.Credits) obj;

    boolean result = true;
    result = result && getLogin()
        .equals(other.getLogin());
    result = result && getPassword()
        .equals(other.getPassword());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + LOGIN_FIELD_NUMBER;
    hash = (53 * hash) + getLogin().hashCode();
    hash = (37 * hash) + PASSWORD_FIELD_NUMBER;
    hash = (53 * hash) + getPassword().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static ru.pobopo.weather.grpc.Credits parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ru.pobopo.weather.grpc.Credits parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ru.pobopo.weather.grpc.Credits parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ru.pobopo.weather.grpc.Credits parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ru.pobopo.weather.grpc.Credits parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ru.pobopo.weather.grpc.Credits parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ru.pobopo.weather.grpc.Credits parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static ru.pobopo.weather.grpc.Credits parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static ru.pobopo.weather.grpc.Credits parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static ru.pobopo.weather.grpc.Credits parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static ru.pobopo.weather.grpc.Credits parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static ru.pobopo.weather.grpc.Credits parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(ru.pobopo.weather.grpc.Credits prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code ru.pobopo.weather.grpc.Credits}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ru.pobopo.weather.grpc.Credits)
      ru.pobopo.weather.grpc.CreditsOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return ru.pobopo.weather.grpc.Auth.internal_static_ru_pobopo_weather_grpc_Credits_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return ru.pobopo.weather.grpc.Auth.internal_static_ru_pobopo_weather_grpc_Credits_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              ru.pobopo.weather.grpc.Credits.class, ru.pobopo.weather.grpc.Credits.Builder.class);
    }

    // Construct using ru.pobopo.weather.grpc.Credits.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      login_ = "";

      password_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return ru.pobopo.weather.grpc.Auth.internal_static_ru_pobopo_weather_grpc_Credits_descriptor;
    }

    public ru.pobopo.weather.grpc.Credits getDefaultInstanceForType() {
      return ru.pobopo.weather.grpc.Credits.getDefaultInstance();
    }

    public ru.pobopo.weather.grpc.Credits build() {
      ru.pobopo.weather.grpc.Credits result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public ru.pobopo.weather.grpc.Credits buildPartial() {
      ru.pobopo.weather.grpc.Credits result = new ru.pobopo.weather.grpc.Credits(this);
      result.login_ = login_;
      result.password_ = password_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof ru.pobopo.weather.grpc.Credits) {
        return mergeFrom((ru.pobopo.weather.grpc.Credits)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(ru.pobopo.weather.grpc.Credits other) {
      if (other == ru.pobopo.weather.grpc.Credits.getDefaultInstance()) return this;
      if (!other.getLogin().isEmpty()) {
        login_ = other.login_;
        onChanged();
      }
      if (!other.getPassword().isEmpty()) {
        password_ = other.password_;
        onChanged();
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      ru.pobopo.weather.grpc.Credits parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (ru.pobopo.weather.grpc.Credits) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object login_ = "";
    /**
     * <code>string login = 1;</code>
     */
    public java.lang.String getLogin() {
      java.lang.Object ref = login_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        login_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string login = 1;</code>
     */
    public com.google.protobuf.ByteString
        getLoginBytes() {
      java.lang.Object ref = login_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        login_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string login = 1;</code>
     */
    public Builder setLogin(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      login_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string login = 1;</code>
     */
    public Builder clearLogin() {
      
      login_ = getDefaultInstance().getLogin();
      onChanged();
      return this;
    }
    /**
     * <code>string login = 1;</code>
     */
    public Builder setLoginBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      login_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object password_ = "";
    /**
     * <code>string password = 2;</code>
     */
    public java.lang.String getPassword() {
      java.lang.Object ref = password_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        password_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string password = 2;</code>
     */
    public com.google.protobuf.ByteString
        getPasswordBytes() {
      java.lang.Object ref = password_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        password_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string password = 2;</code>
     */
    public Builder setPassword(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      password_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string password = 2;</code>
     */
    public Builder clearPassword() {
      
      password_ = getDefaultInstance().getPassword();
      onChanged();
      return this;
    }
    /**
     * <code>string password = 2;</code>
     */
    public Builder setPasswordBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      password_ = value;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:ru.pobopo.weather.grpc.Credits)
  }

  // @@protoc_insertion_point(class_scope:ru.pobopo.weather.grpc.Credits)
  private static final ru.pobopo.weather.grpc.Credits DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new ru.pobopo.weather.grpc.Credits();
  }

  public static ru.pobopo.weather.grpc.Credits getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Credits>
      PARSER = new com.google.protobuf.AbstractParser<Credits>() {
    public Credits parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new Credits(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Credits> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Credits> getParserForType() {
    return PARSER;
  }

  public ru.pobopo.weather.grpc.Credits getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
