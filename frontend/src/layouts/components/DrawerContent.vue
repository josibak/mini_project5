<script setup>
import {
  VerticalNavLink,
  VerticalNavSectionTitle,
} from '@layouts'
</script>

<template>
    <ul>
        <VerticalNavSectionTitle :item="{ heading: '작가관리' }" />
        <VerticalNavLink
            :item="{
                title: '작가',
                to: '/authors',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '원고 작성 관리' }" />
        <VerticalNavLink
            :item="{
                title: '원고',
                to: '/manuscripts',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: 'AI 출간 작업' }" />
        <VerticalNavLink
            :item="{
                title: '출간',
                to: '/publications',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '도서 관리' }" />
        <VerticalNavLink
            :item="{
                title: '도서',
                to: '/books',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '회원 관리' }" />
        <VerticalNavLink
            :item="{
                title: '회원',
                to: '/members',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '회원',
                to: '/userpages',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '구독 관리' }" />
        <VerticalNavLink
            :item="{
                title: '구독',
                to: '/subcriptions',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '포인트 관리' }" />
        <VerticalNavLink
            :item="{
                title: '포인트',
                to: '/pointAccounts',
            }"
        />
    </ul>
</template>
