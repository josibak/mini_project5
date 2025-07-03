
import { useState } from 'react';
import { ArrowLeft, Save, FileText } from 'lucide-react';
import { Link } from 'react-router-dom';
import { Button } from '@/components/ui/button';
import { Textarea } from '@/components/ui/textarea';

const WritingEditor = () => {
  const [formData, setFormData] = useState({
    title: '달빛 아래의 서약',
    content: `달빛이 창문 너머로 스며들던 그 밤, 나는 운명이라는 것이 정말 존재한다는 사실을 깨달았다...

그는 어둠 속에서 나타났다. 마치 달빛이 인간의 형태를 빌린 듯한 모습으로. 그의 눈빛에는 천 년의 세월이 담겨있었고, 그 입술에서 흘러나오는 목소리는 마치 고대의 주문처럼 내 마음 깊숙이 스며들었다.

"당신을 기다려왔습니다." 

그의 첫 마디였다. 나는 그 순간 내 모든 것이 변해버릴 것임을 직감했다. 달빛 아래에서 시작된 우리의 이야기는 운명보다도 강한 무언가였다. 그것은 바로...`
  });

  const [status, setStatus] = useState({
    status: '최종저장',
    wordCount: 53
  });

  const handleSave = () => {
    alert('저장되었습니다.');
  };

  return (
    <div className="min-h-screen bg-warm-brown-50">
      {/* Header */}
      <div className="bg-white border-b border-warm-brown-200">
        <div className="max-w-6xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex items-center justify-between h-16">
            <div className="flex items-center gap-4">
              <Link to="/my-books" className="flex items-center gap-2 text-gray-600 hover:text-gray-900">
                <ArrowLeft className="w-5 h-5" />
                <span>목록으로</span>
              </Link>
              <div className="flex items-center gap-2">
                <FileText className="w-5 h-5 text-warm-brown-600" />
                <span className="font-medium">글 편집</span>
              </div>
            </div>
            
            <div className="flex items-center gap-4">
              <span className="text-sm text-gray-600">{status.wordCount}자</span>
              <Button 
                variant="outline" 
                size="sm"
                className="border-warm-brown-300 text-warm-brown-700"
              >
                임시저장
              </Button>
              <Button 
                size="sm"
                className="bg-warm-brown-700 hover:bg-warm-brown-800 text-white"
                onClick={handleSave}
              >
                최종저장
              </Button>
            </div>
          </div>
        </div>
      </div>

      <div className="max-w-6xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <div className="grid lg:grid-cols-2 gap-8">
          {/* Left Panel - 작품 정보 */}
          <div className="bg-white rounded-lg border border-warm-brown-200 p-6">
            <h2 className="text-lg font-medium text-gray-900 mb-6">작품 정보</h2>
            
            <div className="space-y-4">
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">
                  제목 *
                </label>
                <input
                  type="text"
                  value={formData.title}
                  onChange={(e) => setFormData({...formData, title: e.target.value})}
                  className="w-full px-4 py-2 border border-warm-brown-300 rounded-lg focus:ring-2 focus:ring-warm-brown-500 focus:border-transparent"
                />
              </div>
              
              <div className="pt-4 border-t border-warm-brown-200">
                <div className="text-sm text-gray-600">
                  <div>상태: {status.status}</div>
                  <div>글자 수: {status.wordCount}자</div>
                </div>
              </div>
            </div>
          </div>

          {/* Right Panel - 본문 작성 */}
          <div className="bg-white rounded-lg border border-warm-brown-200 p-6">
            <h2 className="text-lg font-medium text-gray-900 mb-6">본문 작성</h2>
            
            <Textarea
              value={formData.content}
              onChange={(e) => setFormData({...formData, content: e.target.value})}
              rows={20}
              className="w-full border border-warm-brown-300 rounded-lg focus:ring-2 focus:ring-warm-brown-500 focus:border-transparent resize-none text-base leading-relaxed"
              placeholder="여기에 작품을 작성해주세요.

팁:
- 단락 구분을 명확히 해주세요
- 대화는 따옴표로 구분해주세요  
- 최소 1,000자 이상 작성해야 최종저장이 가능합니다"
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default WritingEditor;
