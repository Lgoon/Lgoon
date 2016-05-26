// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: IM.RabbitMQTest.proto

package com.lgoo.proto.IM.RabbitMQTest;

public final class IMRabbitMQTest {
  private IMRabbitMQTest() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }
  public interface PushMsgTestOrBuilder extends
      // @@protoc_insertion_point(interface_extends:IM.RabbitMQTest.PushMsgTest)
      com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <code>required uint32 ServerId = 1;</code>
     */
    boolean hasServerId();
    /**
     * <code>required uint32 ServerId = 1;</code>
     */
    int getServerId();

    /**
     * <code>required uint32 MsgType = 2;</code>
     */
    boolean hasMsgType();
    /**
     * <code>required uint32 MsgType = 2;</code>
     */
    int getMsgType();

    /**
     * <code>required uint64 FromId = 3;</code>
     */
    boolean hasFromId();
    /**
     * <code>required uint64 FromId = 3;</code>
     */
    long getFromId();

    /**
     * <code>required uint32 SearchId = 4;</code>
     */
    boolean hasSearchId();
    /**
     * <code>required uint32 SearchId = 4;</code>
     */
    int getSearchId();

    /**
     * <code>optional uint64 ToId = 5;</code>
     */
    boolean hasToId();
    /**
     * <code>optional uint64 ToId = 5;</code>
     */
    long getToId();

    /**
     * <code>optional uint32 DevType = 6;</code>
     */
    boolean hasDevType();
    /**
     * <code>optional uint32 DevType = 6;</code>
     */
    int getDevType();

    /**
     * <code>required string Content = 7;</code>
     */
    boolean hasContent();
    /**
     * <code>required string Content = 7;</code>
     */
    java.lang.String getContent();
    /**
     * <code>required string Content = 7;</code>
     */
    com.google.protobuf.ByteString
        getContentBytes();
  }
  /**
   * Protobuf type {@code IM.RabbitMQTest.PushMsgTest}
   */
  public static final class PushMsgTest extends
      com.google.protobuf.GeneratedMessageLite implements
      // @@protoc_insertion_point(message_implements:IM.RabbitMQTest.PushMsgTest)
      PushMsgTestOrBuilder {
    // Use PushMsgTest.newBuilder() to construct.
    private PushMsgTest(com.google.protobuf.GeneratedMessageLite.Builder builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private PushMsgTest(boolean noInit) { this.unknownFields = com.google.protobuf.ByteString.EMPTY;}

    private static final PushMsgTest defaultInstance;
    public static PushMsgTest getDefaultInstance() {
      return defaultInstance;
    }

    public PushMsgTest getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.ByteString unknownFields;
    private PushMsgTest(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.ByteString.Output unknownFieldsOutput =
          com.google.protobuf.ByteString.newOutput();
      com.google.protobuf.CodedOutputStream unknownFieldsCodedOutput =
          com.google.protobuf.CodedOutputStream.newInstance(
              unknownFieldsOutput);
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFieldsCodedOutput,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              serverId_ = input.readUInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              msgType_ = input.readUInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              fromId_ = input.readUInt64();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              searchId_ = input.readUInt32();
              break;
            }
            case 40: {
              bitField0_ |= 0x00000010;
              toId_ = input.readUInt64();
              break;
            }
            case 48: {
              bitField0_ |= 0x00000020;
              devType_ = input.readUInt32();
              break;
            }
            case 58: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000040;
              content_ = bs;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        try {
          unknownFieldsCodedOutput.flush();
        } catch (java.io.IOException e) {
        // Should not happen
        } finally {
          unknownFields = unknownFieldsOutput.toByteString();
        }
        makeExtensionsImmutable();
      }
    }
    public static com.google.protobuf.Parser<PushMsgTest> PARSER =
        new com.google.protobuf.AbstractParser<PushMsgTest>() {
      public PushMsgTest parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new PushMsgTest(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<PushMsgTest> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int SERVERID_FIELD_NUMBER = 1;
    private int serverId_;
    /**
     * <code>required uint32 ServerId = 1;</code>
     */
    public boolean hasServerId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required uint32 ServerId = 1;</code>
     */
    public int getServerId() {
      return serverId_;
    }

    public static final int MSGTYPE_FIELD_NUMBER = 2;
    private int msgType_;
    /**
     * <code>required uint32 MsgType = 2;</code>
     */
    public boolean hasMsgType() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required uint32 MsgType = 2;</code>
     */
    public int getMsgType() {
      return msgType_;
    }

    public static final int FROMID_FIELD_NUMBER = 3;
    private long fromId_;
    /**
     * <code>required uint64 FromId = 3;</code>
     */
    public boolean hasFromId() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required uint64 FromId = 3;</code>
     */
    public long getFromId() {
      return fromId_;
    }

    public static final int SEARCHID_FIELD_NUMBER = 4;
    private int searchId_;
    /**
     * <code>required uint32 SearchId = 4;</code>
     */
    public boolean hasSearchId() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>required uint32 SearchId = 4;</code>
     */
    public int getSearchId() {
      return searchId_;
    }

    public static final int TOID_FIELD_NUMBER = 5;
    private long toId_;
    /**
     * <code>optional uint64 ToId = 5;</code>
     */
    public boolean hasToId() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>optional uint64 ToId = 5;</code>
     */
    public long getToId() {
      return toId_;
    }

    public static final int DEVTYPE_FIELD_NUMBER = 6;
    private int devType_;
    /**
     * <code>optional uint32 DevType = 6;</code>
     */
    public boolean hasDevType() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <code>optional uint32 DevType = 6;</code>
     */
    public int getDevType() {
      return devType_;
    }

    public static final int CONTENT_FIELD_NUMBER = 7;
    private java.lang.Object content_;
    /**
     * <code>required string Content = 7;</code>
     */
    public boolean hasContent() {
      return ((bitField0_ & 0x00000040) == 0x00000040);
    }
    /**
     * <code>required string Content = 7;</code>
     */
    public java.lang.String getContent() {
      java.lang.Object ref = content_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          content_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string Content = 7;</code>
     */
    public com.google.protobuf.ByteString
        getContentBytes() {
      java.lang.Object ref = content_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        content_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private void initFields() {
      serverId_ = 0;
      msgType_ = 0;
      fromId_ = 0L;
      searchId_ = 0;
      toId_ = 0L;
      devType_ = 0;
      content_ = "";
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasServerId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasMsgType()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasFromId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasSearchId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasContent()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeUInt32(1, serverId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeUInt32(2, msgType_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeUInt64(3, fromId_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeUInt32(4, searchId_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeUInt64(5, toId_);
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        output.writeUInt32(6, devType_);
      }
      if (((bitField0_ & 0x00000040) == 0x00000040)) {
        output.writeBytes(7, getContentBytes());
      }
      output.writeRawBytes(unknownFields);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(1, serverId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(2, msgType_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt64Size(3, fromId_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(4, searchId_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt64Size(5, toId_);
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(6, devType_);
      }
      if (((bitField0_ & 0x00000040) == 0x00000040)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(7, getContentBytes());
      }
      size += unknownFields.size();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.lgoo.proto.IM.RabbitMQTest.IMRabbitMQTest.PushMsgTest parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.lgoo.proto.IM.RabbitMQTest.IMRabbitMQTest.PushMsgTest parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.lgoo.proto.IM.RabbitMQTest.IMRabbitMQTest.PushMsgTest parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.lgoo.proto.IM.RabbitMQTest.IMRabbitMQTest.PushMsgTest parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.lgoo.proto.IM.RabbitMQTest.IMRabbitMQTest.PushMsgTest parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.lgoo.proto.IM.RabbitMQTest.IMRabbitMQTest.PushMsgTest parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.lgoo.proto.IM.RabbitMQTest.IMRabbitMQTest.PushMsgTest parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.lgoo.proto.IM.RabbitMQTest.IMRabbitMQTest.PushMsgTest parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.lgoo.proto.IM.RabbitMQTest.IMRabbitMQTest.PushMsgTest parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.lgoo.proto.IM.RabbitMQTest.IMRabbitMQTest.PushMsgTest parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.lgoo.proto.IM.RabbitMQTest.IMRabbitMQTest.PushMsgTest prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    /**
     * Protobuf type {@code IM.RabbitMQTest.PushMsgTest}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageLite.Builder<
          com.lgoo.proto.IM.RabbitMQTest.IMRabbitMQTest.PushMsgTest, Builder>
        implements
        // @@protoc_insertion_point(builder_implements:IM.RabbitMQTest.PushMsgTest)
        com.lgoo.proto.IM.RabbitMQTest.IMRabbitMQTest.PushMsgTestOrBuilder {
      // Construct using IM.RabbitMQTest.IMRabbitMQTest.PushMsgTest.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        serverId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        msgType_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        fromId_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000004);
        searchId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        toId_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000010);
        devType_ = 0;
        bitField0_ = (bitField0_ & ~0x00000020);
        content_ = "";
        bitField0_ = (bitField0_ & ~0x00000040);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.lgoo.proto.IM.RabbitMQTest.IMRabbitMQTest.PushMsgTest getDefaultInstanceForType() {
        return com.lgoo.proto.IM.RabbitMQTest.IMRabbitMQTest.PushMsgTest.getDefaultInstance();
      }

      public com.lgoo.proto.IM.RabbitMQTest.IMRabbitMQTest.PushMsgTest build() {
        com.lgoo.proto.IM.RabbitMQTest.IMRabbitMQTest.PushMsgTest result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.lgoo.proto.IM.RabbitMQTest.IMRabbitMQTest.PushMsgTest buildPartial() {
        com.lgoo.proto.IM.RabbitMQTest.IMRabbitMQTest.PushMsgTest result = new com.lgoo.proto.IM.RabbitMQTest.IMRabbitMQTest.PushMsgTest(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.serverId_ = serverId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.msgType_ = msgType_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.fromId_ = fromId_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.searchId_ = searchId_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.toId_ = toId_;
        if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
          to_bitField0_ |= 0x00000020;
        }
        result.devType_ = devType_;
        if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
          to_bitField0_ |= 0x00000040;
        }
        result.content_ = content_;
        result.bitField0_ = to_bitField0_;
        return result;
      }

      public Builder mergeFrom(com.lgoo.proto.IM.RabbitMQTest.IMRabbitMQTest.PushMsgTest other) {
        if (other == com.lgoo.proto.IM.RabbitMQTest.IMRabbitMQTest.PushMsgTest.getDefaultInstance()) return this;
        if (other.hasServerId()) {
          setServerId(other.getServerId());
        }
        if (other.hasMsgType()) {
          setMsgType(other.getMsgType());
        }
        if (other.hasFromId()) {
          setFromId(other.getFromId());
        }
        if (other.hasSearchId()) {
          setSearchId(other.getSearchId());
        }
        if (other.hasToId()) {
          setToId(other.getToId());
        }
        if (other.hasDevType()) {
          setDevType(other.getDevType());
        }
        if (other.hasContent()) {
          bitField0_ |= 0x00000040;
          content_ = other.content_;
          
        }
        setUnknownFields(
            getUnknownFields().concat(other.unknownFields));
        return this;
      }

      public final boolean isInitialized() {
        if (!hasServerId()) {
          
          return false;
        }
        if (!hasMsgType()) {
          
          return false;
        }
        if (!hasFromId()) {
          
          return false;
        }
        if (!hasSearchId()) {
          
          return false;
        }
        if (!hasContent()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.lgoo.proto.IM.RabbitMQTest.IMRabbitMQTest.PushMsgTest parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.lgoo.proto.IM.RabbitMQTest.IMRabbitMQTest.PushMsgTest) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int serverId_ ;
      /**
       * <code>required uint32 ServerId = 1;</code>
       */
      public boolean hasServerId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required uint32 ServerId = 1;</code>
       */
      public int getServerId() {
        return serverId_;
      }
      /**
       * <code>required uint32 ServerId = 1;</code>
       */
      public Builder setServerId(int value) {
        bitField0_ |= 0x00000001;
        serverId_ = value;
        
        return this;
      }
      /**
       * <code>required uint32 ServerId = 1;</code>
       */
      public Builder clearServerId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        serverId_ = 0;
        
        return this;
      }

      private int msgType_ ;
      /**
       * <code>required uint32 MsgType = 2;</code>
       */
      public boolean hasMsgType() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required uint32 MsgType = 2;</code>
       */
      public int getMsgType() {
        return msgType_;
      }
      /**
       * <code>required uint32 MsgType = 2;</code>
       */
      public Builder setMsgType(int value) {
        bitField0_ |= 0x00000002;
        msgType_ = value;
        
        return this;
      }
      /**
       * <code>required uint32 MsgType = 2;</code>
       */
      public Builder clearMsgType() {
        bitField0_ = (bitField0_ & ~0x00000002);
        msgType_ = 0;
        
        return this;
      }

      private long fromId_ ;
      /**
       * <code>required uint64 FromId = 3;</code>
       */
      public boolean hasFromId() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required uint64 FromId = 3;</code>
       */
      public long getFromId() {
        return fromId_;
      }
      /**
       * <code>required uint64 FromId = 3;</code>
       */
      public Builder setFromId(long value) {
        bitField0_ |= 0x00000004;
        fromId_ = value;
        
        return this;
      }
      /**
       * <code>required uint64 FromId = 3;</code>
       */
      public Builder clearFromId() {
        bitField0_ = (bitField0_ & ~0x00000004);
        fromId_ = 0L;
        
        return this;
      }

      private int searchId_ ;
      /**
       * <code>required uint32 SearchId = 4;</code>
       */
      public boolean hasSearchId() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>required uint32 SearchId = 4;</code>
       */
      public int getSearchId() {
        return searchId_;
      }
      /**
       * <code>required uint32 SearchId = 4;</code>
       */
      public Builder setSearchId(int value) {
        bitField0_ |= 0x00000008;
        searchId_ = value;
        
        return this;
      }
      /**
       * <code>required uint32 SearchId = 4;</code>
       */
      public Builder clearSearchId() {
        bitField0_ = (bitField0_ & ~0x00000008);
        searchId_ = 0;
        
        return this;
      }

      private long toId_ ;
      /**
       * <code>optional uint64 ToId = 5;</code>
       */
      public boolean hasToId() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      /**
       * <code>optional uint64 ToId = 5;</code>
       */
      public long getToId() {
        return toId_;
      }
      /**
       * <code>optional uint64 ToId = 5;</code>
       */
      public Builder setToId(long value) {
        bitField0_ |= 0x00000010;
        toId_ = value;
        
        return this;
      }
      /**
       * <code>optional uint64 ToId = 5;</code>
       */
      public Builder clearToId() {
        bitField0_ = (bitField0_ & ~0x00000010);
        toId_ = 0L;
        
        return this;
      }

      private int devType_ ;
      /**
       * <code>optional uint32 DevType = 6;</code>
       */
      public boolean hasDevType() {
        return ((bitField0_ & 0x00000020) == 0x00000020);
      }
      /**
       * <code>optional uint32 DevType = 6;</code>
       */
      public int getDevType() {
        return devType_;
      }
      /**
       * <code>optional uint32 DevType = 6;</code>
       */
      public Builder setDevType(int value) {
        bitField0_ |= 0x00000020;
        devType_ = value;
        
        return this;
      }
      /**
       * <code>optional uint32 DevType = 6;</code>
       */
      public Builder clearDevType() {
        bitField0_ = (bitField0_ & ~0x00000020);
        devType_ = 0;
        
        return this;
      }

      private java.lang.Object content_ = "";
      /**
       * <code>required string Content = 7;</code>
       */
      public boolean hasContent() {
        return ((bitField0_ & 0x00000040) == 0x00000040);
      }
      /**
       * <code>required string Content = 7;</code>
       */
      public java.lang.String getContent() {
        java.lang.Object ref = content_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            content_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string Content = 7;</code>
       */
      public com.google.protobuf.ByteString
          getContentBytes() {
        java.lang.Object ref = content_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          content_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string Content = 7;</code>
       */
      public Builder setContent(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000040;
        content_ = value;
        
        return this;
      }
      /**
       * <code>required string Content = 7;</code>
       */
      public Builder clearContent() {
        bitField0_ = (bitField0_ & ~0x00000040);
        content_ = getDefaultInstance().getContent();
        
        return this;
      }
      /**
       * <code>required string Content = 7;</code>
       */
      public Builder setContentBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000040;
        content_ = value;
        
        return this;
      }

      // @@protoc_insertion_point(builder_scope:IM.RabbitMQTest.PushMsgTest)
    }

    static {
      defaultInstance = new PushMsgTest(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:IM.RabbitMQTest.PushMsgTest)
  }


  static {
  }

  // @@protoc_insertion_point(outer_class_scope)
}