// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: auth.proto

package ru.pobopo.weather.grpc;

public final class Auth {
  private Auth() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ru_pobopo_weather_grpc_Credits_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ru_pobopo_weather_grpc_Credits_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\nauth.proto\022\026ru.pobopo.weather.grpc\032\036go" +
      "ogle/protobuf/wrappers.proto\"*\n\007Credits\022" +
      "\r\n\005login\030\001 \001(\t\022\020\n\010password\030\002 \001(\t2X\n\013Auth" +
      "Service\022I\n\010AuthUser\022\037.ru.pobopo.weather." +
      "grpc.Credits\032\032.google.protobuf.BoolValue" +
      "\"\000B\002P\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.WrappersProto.getDescriptor(),
        }, assigner);
    internal_static_ru_pobopo_weather_grpc_Credits_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ru_pobopo_weather_grpc_Credits_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ru_pobopo_weather_grpc_Credits_descriptor,
        new java.lang.String[] { "Login", "Password", });
    com.google.protobuf.WrappersProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}